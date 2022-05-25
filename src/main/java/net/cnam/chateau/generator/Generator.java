package net.cnam.chateau.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.enemy.boss.BossMartinez;
import net.cnam.chateau.structure.*;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.DownStair;
import net.cnam.chateau.structure.block.UpStair;
import net.cnam.chateau.structure.block.Wall;
import net.cnam.chateau.structure.block.door.Door;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.array.ArrayUtils;

import java.util.*;

import static net.cnam.chateau.generator.GUtils.*;

/**
 * Classe pour la génération de la map
 */
public class Generator {

    private static final int MIN_SIZE_STAGE = 25; //taille mini d'un étage
    private static final int MAX_SIZE_STAGE = 35; //taille maxi d'un étage
    private static final int MIN_STAGE = 2; //nombre mini d'étage
    private static final int MAX_STAGE = 2; //nombre maxi d'étage
    private static final int MIN_SIZE_ROOM = 6; //taille mini d'une pièce
    private static final int MAX_SIZE_ROOM = MIN_SIZE_ROOM * 2 + 2; //taille max d'une pièce
    private static final int NB_ITERATION_MIN = 3; //nombre de division minimum des étages
    private static final int NB_ITERATION_MAX = 5; //nombre de division maximum supplémentaire des étages
    private static final int POURCENT_DIVIDE = 10; //ajusteur pour savoir si une pièce se re divise dans la deuxième phase de division
    private static final int MIN_BLOCKS = 1; // nombre de bloc décoratifs minimum par pièce
    private static final int MAX_BLOCKS = 3; // nombre de bloc maximum par pièce

    private final App app;
    private final long seed;
    private final Random random;
    private Location playerStartLocation;

    /**
     * Constructeur
     *
     * @param app  L'application
     * @param seed long qui permet de générer la carte de façon procédural
     */
    public Generator(App app, long seed) {
        this.app = app;
        this.seed = seed;
        this.random = new Random(seed);
    }

    /**
     * Méthode qui génère un chateau de A à Z. Appelle la méthode qui génère des
     * étages
     *
     * @return un Chateau
     */
    public Castle generateCastle() {
        return new Castle(this.generateStages(), this.playerStartLocation, seed);
    }

    /**
     * Méthode qui génère des étages fini.
     *
     * @return un tableau d'étage qui composera le chateau (salle de boss non
     * comprise)
     */
    public Stage[] generateStages() {
        Stage[] stages = new Stage[this.random.nextInt(MIN_STAGE, MAX_STAGE + 1)];

        //Pour chaque étage on le génère
        for (int i = 0; i < stages.length; i++) {
            Stage stage = generateStage();
            //Pour chaque room de chaque étage on la génère
            for (Room room : stage.getRooms()) {
                generateRoom(room);
            }
            stages[i] = stage;
        }

        // On récupère la première room
        // On place le joueur aléatoirement dans l'étage le plus haut (ou le plus bas à voir)
        Room firstRoom = stages[0].getRooms()[random.nextInt(0, stages[0].getRooms().length)];
        firstRoom.setVisible(true);
        this.playerStartLocation = getDefaultPlayerLocation(firstRoom);

        //On boucle sur chaque étage
        for (int i = 0; i < stages.length; i++) {
            Stage stage = stages[i];
            List<GRoom> gRooms = generateStageWalls(stage); // On génère les murs et on récupère la liste des petits murs qui lie 2 pièces

            // On place les sorties
            // Si c'est l'étage 1 : on met juste une sortie
            UpStair exitStair = triTopo(app.getConsole(), firstRoom, gRooms, random);

            //ajoute l'escalier du bas en haut
            if (i < stages.length - 1) {
                Stage stageEntry = stages[i + 1];
                DownStair entryStair = new DownStair();
                entryStair.setOtherStair(exitStair);
                entryStair.setStage(stageEntry);
                exitStair.setOtherStair(entryStair);

                // On gère le placement de l'escalier à l'étage du dessus en essayant de rendre la position un minimum cohérente
                int xOldExit = exitStair.getLocation().getX();
                int yOldExit = exitStair.getLocation().getY();

                // Si xOldExit est en dehors du champ X on le recadre pour le ramener à la room la plus proche
                while (stageEntry.getLength() <= xOldExit) {
                    xOldExit -= 1;
                }
                // Si yOldExit est en dehors du champ Y on le recadre pour le ramener à la room la plus proche
                while (stageEntry.getHeight() <= yOldExit) {
                    yOldExit -= 1;
                }

                // On cherche et récupère la room qui est associé aux coordonnées (xOldExit, yOldExit) par rapport à l'étage
                try {
                    firstRoom = stageEntry.getRoom(xOldExit, yOldExit);
                } catch (CoordinatesOutOfBoundsException ex) {
                    firstRoom = stageEntry.getRooms()[0];
                }

                // On regarde ou on peut placer l'escalier d'entrée de l'étage et on récupère les coordoonnées
                Location location = findPosition(this.random, firstRoom);
                int x = location.getX();
                int y = location.getY();

                //on ajoute l'escalier d'entrée dans la pièce du nouvelle étage
                firstRoom.getBlocks()[x][y] = entryStair;
                //on récupère la location dans la pièce de l'escalier et on lui ajoute dans ses champs
                entryStair.setLocation(new Location(firstRoom.getLocation().getX() + x, firstRoom.getLocation().getY() + y));

            }
            // On ajoute au champ de l'escalier de sortie de l'étage actuellement traité, l'étage en question auquel il appartient
            // également on ferme la pièce et on cache une clé dans une des pièces antérieur
            exitStair.setStage(stage);

            // Si on arrive au dernir étage, on ajoute un étage supplémentaire pour accueillir la pièce du boss
            if (i == stages.length - 1) {
                RoomBoss lastRoom = new RoomBoss();
                Stage stageBoss = new Stage(new Room[]{lastRoom}, lastRoom.getLength(), lastRoom.getHeight());
                lastRoom.getEntry().setOtherStair(exitStair);
                lastRoom.getEntry().setStage(stageBoss);
                exitStair.setOtherStair(lastRoom.getEntry());
                stageBoss.getEntities().add(new BossMartinez(app, stageBoss, new Location(6, 4)));
            }
        }

        return stages;
    }

    /**
     * Méthode qui génère un étage en procédant à des découpes.
     *
     * @return un étage
     */
    public Stage generateStage() {
        // On défini la taille que fera l'étage entre 2 bornes (constantes) 
        int stageLength = this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE + 1);
        int stageHeight = this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE + 1);

        // On créer un tableau contenant la room de base permettant la découpe de l'étage
        Room[] rooms = new Room[1];
        rooms[0] = new Room(new Location(0, 0), new Block[stageLength][stageHeight]);
        Block[][] blocksMainRoom = rooms[0].getBlocks();

        // On génère les murs de la première pièce
        for (int x = 0; x < blocksMainRoom.length; x++) {
            for (int y = 0; y < blocksMainRoom[x].length; y++) {
                if ((x == 0 || x == blocksMainRoom.length - 1 || y == 0 || y == blocksMainRoom[x].length - 1) && blocksMainRoom[x][y] == null) {
                    blocksMainRoom[x][y] = new Wall();
                }
            }
        }

        // On procède à la découpe des pièces avec un minimum et un maximum d'itération (constantes)
        int pourcentage = 0;
        for (int i = 0; i < NB_ITERATION_MAX; i++) {
            // A chaque tour de boucle, si on a fait le nombre d'itération minimum, on ajoute un pourcentage de division
            // Le pourcentage sert à définir si oui ou non on tente de diviser la pièce
            if (i >= NB_ITERATION_MIN) {
                pourcentage += POURCENT_DIVIDE;
            }
            int nbRooms = rooms.length;
            for (int j = 0; j < nbRooms; j++) {
                if (pourcentage != 0 && this.random.nextInt(1, 101) + pourcentage > 50) {
                    continue;
                }
                //on tente de diviser la pièce
                Room roomDivided = divideRoom(rooms[j]);
                if (roomDivided == null) {
                    continue;
                }
                // Si la pièce a été divisé, on ajoute la nouvelle pièce au tableau
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided);
            }
        }
        rooms = verifyRoomSize(rooms);
        return new Stage(rooms, stageLength, stageHeight);
    }

    /**
     * Méthode permettant de vérifier que les pièces ne sont pas trop grande, sinon division
     *
     * @param rooms tableau de pièce d'un étage
     */
    public Room[] verifyRoomSize(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            while (rooms[i].getLength() > MAX_SIZE_ROOM || rooms[i].getHeight() > MAX_SIZE_ROOM) {
                Room roomDivided = divideRoom(rooms[i]);
                if (roomDivided == null) {
                    continue;
                }
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided);
            }
        }
        return rooms;
    }

    /**
     * Méthode permettant de définir le sens de découpe. Découpe dans le sens ou
     * la pièce est la plus longue Si longueur = largeur : découpe random
     *
     * @param room la pièce à découper
     * @return un couple de pièce résultant de la pièce passé en paramètre
     */
    public Room divideRoom(Room room) {
        if (room.getLength() > room.getHeight()) {
            return divideRoomLength(room);
        }
        if (room.getLength() < room.getHeight()) {
            return divideRoomWidth(room);
        }
        if (this.random.nextBoolean()) {
            return divideRoomLength(room);
        }
        return divideRoomWidth(room);
    }

    /**
     * Méthode pour diviser une pièce en deux pièces en découpant en longueur.
     * Si renvoie null c'est que la taille de la pièce n'est pas suffisante pour
     * être divisé
     *
     * @param roomLeft la pièce à diviser, qui va devenir la pièce de gauche
     * @return roomRight la pièce pièce de droite
     */
    public Room divideRoomLength(Room roomLeft) {
        if (roomLeft.getLength() / 2 <= MIN_SIZE_ROOM) {
            return null;
        }
        int cut = random.nextInt(MIN_SIZE_ROOM, roomLeft.getLength() - MIN_SIZE_ROOM + 1);

        Block[][] oldBlocks = roomLeft.getBlocks();

        for (int y = 1; y < oldBlocks[0].length - 1; y++) {
            oldBlocks[cut][y] = new Wall();
        }

        // Nouvelle pièce à gauche
        Block[][] newBlocksRoomLeft = new Block[cut + 1][roomLeft.getHeight()];
        // Nouvelle pièce à droite
        Block[][] newBlocksRoomRight = new Block[roomLeft.getLength() - cut][roomLeft.getHeight()];

        for (int x = 0; x < oldBlocks.length; x++) {
            for (int y = 0; y < oldBlocks[0].length; y++) {
                if (x <= cut) {
                    newBlocksRoomLeft[x][y] = oldBlocks[x][y];
                }
                if (x >= cut) {
                    newBlocksRoomRight[x - cut][y] = oldBlocks[x][y];
                }
            }
        }

        Room roomRight = new Room(new Location(roomLeft.getLocation().getX() + cut, roomLeft.getLocation().getY()), newBlocksRoomRight);
        roomLeft.setBlocks(newBlocksRoomLeft);

        return roomRight;
    }

    /**
     * Méthode pour diviser une pièce en deux pièces en découpant en largeur. Si
     * renvoie null c'est que la taille de la pièce n'est pas suffisante pour
     * être divisé
     *
     * @param roomTop la pièce à diviser
     * @return un couple de pièce résultant de la découpe de la pièce passé en
     * paramètre
     */
    public Room divideRoomWidth(Room roomTop) {
        if (roomTop.getHeight() / 2 <= MIN_SIZE_ROOM) {
            return null;
        }
        int cut = random.nextInt(MIN_SIZE_ROOM, roomTop.getHeight() - MIN_SIZE_ROOM + 1);

        Block[][] oldBlocks = roomTop.getBlocks();

        for (int x = 1; x < oldBlocks.length - 1; x++) {
            oldBlocks[x][cut] = new Wall();
        }

        // Nouvelle pièce en haut
        Block[][] newBlocksRoomTop = new Block[roomTop.getLength()][cut + 1];
        // Nouvelle pièce en bas
        Block[][] newBlocksRoomBot = new Block[roomTop.getLength()][oldBlocks[0].length - cut];

        for (int x = 0; x < oldBlocks.length; x++) {
            for (int y = 0; y < oldBlocks[0].length; y++) {
                if (y <= cut) {
                    newBlocksRoomTop[x][y] = oldBlocks[x][y];
                }
                if (y >= cut) {
                    newBlocksRoomBot[x][y - cut] = oldBlocks[x][y];
                }
            }
        }

        Room roomBot = new Room(new Location(roomTop.getLocation().getX(), roomTop.getLocation().getY() + cut), newBlocksRoomBot);
        roomTop.setBlocks(newBlocksRoomTop);

        return roomBot;
    }

    /**
     * Méthode permettant de générer l'intérieur d'une pièce. Met un nombre
     * aléatoire de bloc dans la pièce défini entre deux bornes
     *
     * @param room la pièce à remplir
     */
    public void generateRoom(Room room) {
        int numberBlocks = random.nextInt(MIN_BLOCKS, MAX_BLOCKS + 1);
        // On ajoute dans la pièce le nombre de blocs défini au dessus
        for (int i = 0; i < numberBlocks; i++) {
            Location location = findPosition(this.random, room);
            room.getBlocks()[location.getX()][location.getY()] = pickRandomBlock(app, this.random);
        }
    }

    /**
     * Méthode pour placer le player dans la première room de façon aléatoire.
     *
     * @param room room de départ
     * @return La location dans l'étage
     */
    public Location getDefaultPlayerLocation(Room room) {
        Location location = findPosition(this.random, room);
        return new Location(room.getLocation().getX() + location.getX(), room.getLocation().getY() + location.getY());
    }

    private List<GRoom> generateStageWalls(Stage generatedStage) {
        // Génération des murs
        List<GRoom> rooms = new LinkedList<>();
        List<GRoomWall> roomsWalls = new LinkedList<>(); // Contient tout les murs de toutes les pièces
        // On génère les murs des pièces
        int nb = 0;
        for (Room room : generatedStage.getRooms()) {
            GRoom genRoom = new GRoom(room, generatedStage, ++nb);
            rooms.add(genRoom);
            roomsWalls.addAll(genRoom.getWalls());
        }

        // On vérifie pour tout les murs lesquels se superposent
        // Pour toutes les pièces
        for (GRoom room : rooms) {
            // On boucles sur tout lees murs de la pièce (room)
            for (GRoomWall wall : room.getWalls()) {
                // On regarde dans la liste qui contient tous les murs si il y a un mur qui se superpose avec ce mur (wall)
                for (GRoomWall tempWall : roomsWalls) {
                    // On vérifie aussi que le mur (wall) n'est pas celui qui resort de la liste totale des murs
                    if (wall != tempWall && wall.overlapWall(tempWall)) {
                        // On ajoute (tempWall) à la liste des murs qui se superpose sur wall
                        wall.getSideWalls().add(tempWall);
                    }
                }
            }
        }

        // On créer les murs individuels
        List<GWall> walls = new ArrayList<>(); // Contient tout les murs
        // Pour toutes les pièces
        for (GRoom room : rooms) {
            // On boucles sur tout les murs de la pièce (room)
            for (GRoomWall wall : room.getWalls()) {
                // On regarde dans la liste qui contient tous les murs si il y a un mur qui se superpose avec ce mur (wall)
                for (GRoomWall tempWall : wall.getSideWalls()) {
                    GWall gWall = wall.createWall(tempWall, random);
                    if (!walls.contains(gWall)) {
                        walls.add(gWall);
                    }
                }
            }
        }

        // Algo de création des portes
        while (!isGeneratingStageWallsFinished(rooms)) {
            while (true) {
                GWall wall = walls.get(random.nextInt(walls.size()));
                GRoom room1 = wall.getRoomOne();
                int maze1 = room1.getMazeNb();
                GRoom room2 = wall.getRoomTwo();
                int maze2 = room2.getMazeNb();
                if (maze1 != maze2) {
                    for (GRoom room : rooms) {
                        if (room.getMazeNb() == maze2) {
                            room.setMazeNb(maze1);
                        }
                    }
                    breakWall(generatedStage, wall);
                    break;
                }
            }
        }

        // On fait la liste des murs qui ne sont pas cassés
        List<GWall> wallsUnbreaked = new ArrayList<>();
        for (GWall wall : walls) {
            if (!wall.isBreaked()) {
                wallsUnbreaked.add(wall);
            }
        }

        // On fait des trous dans des murs au hasard
        for (int i = 0; i < random.nextInt(wallsUnbreaked.size() / 6, wallsUnbreaked.size() / 3); i++) {
            breakWall(generatedStage, wallsUnbreaked.remove(random.nextInt(wallsUnbreaked.size())));
        }
        return rooms;
    }

    private boolean isGeneratingStageWallsFinished(List<GRoom> rooms) {
        Iterator<GRoom> iterator = rooms.iterator();
        int nb = iterator.next().getMazeNb();
        while (iterator.hasNext()) {
            int temp = iterator.next().getMazeNb();
            if (nb != temp) {
                return false;
            }
        }
        return true;
    }

    private void breakWall(Stage stage, GWall wall) {
        try {
            List<Location> possibleBreakPoints = wall.getWallsLocations();
            if (possibleBreakPoints.isEmpty()) {
                return;
            }
            Location breakPoint = possibleBreakPoints.remove(random.nextInt(possibleBreakPoints.size()));
            stage.setBlock(breakPoint.getX(), breakPoint.getY(), getDoor(random, stage, wall.getRoomOne().getRoom(), wall.getRoomTwo().getRoom(), app));
        } catch (CoordinatesOutOfBoundsException ignored) {
        }
    }

    /**
     * Méthode permettant de récupérer la seed du jeu.
     *
     * @return seed long correspondant à la graine
     */
    public long getSeed() {
        return seed;
    }
}
