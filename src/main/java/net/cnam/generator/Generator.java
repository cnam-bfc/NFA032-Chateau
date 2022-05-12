package net.cnam.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.cnam.utils.Location;
import net.cnam.structure.*;
import net.cnam.structure.block.*;
import net.cnam.utils.array.ArrayUtils;

/**
 * Classe pour la génération de la map
 */
public class Generator {

    private static final int MIN_SIZE_STAGE = 40; //taille mini d'un étage
    private static final int MAX_SIZE_STAGE = 50; //taille maxi d'un étage
    private static final int MIN_STAGE = 3; //nombre mini d'étage
    private static final int MAX_STAGE = 5; //nombre maxi d'étage
    private static final int MIN_SIZE_ROOM = 6; //taille mini d'une pièce
    private static final int NB_ITERATION_MIN = 3; //nombre de division minimum des étages
    private static final int NB_ITERATION_MAX = 5; //nombre de division maximum supplémentaire des étages
    private static final int POURCENT_DIVIDE = 10; //ajusteur pour savoir si une pièce se re divise dans la deuxième phase de division

    private final long seed;
    private final Random random;

    /**
     * Constructeur
     *
     * @param seed long qui permet de générer la carte de façon procédural
     */
    public Generator(long seed) {
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
        return new Castle(this.generateStages(), seed);
    }

    /**
     * Méthode qui génère des étages. Appelle la méthode qui génère un étage
     *
     * @return un tableau d'étage
     */
    public Stage[] generateStages() {
        Stage[] result = new Stage[this.random.nextInt(MIN_STAGE, MAX_STAGE + 1)];

        for (int i = 0; i < result.length; i++) {
            Stage stage = generateStage();
            result[i] = stage;
        }

        return result;
    }

    /**
     * Méthode qui génère un étage.
     *
     * @return un étage
     */
    public Stage generateStage() {
        int stageLength = this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE + 1);
        int stageWidth = this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE + 1);

        Room[] rooms = new Room[1];
        rooms[0] = new Room(new Location(0, 0), new Block[stageLength][stageWidth]); //room de base
        Block[][] blocksMainRoom = rooms[0].getBlocks();

        // On génère les murs de la pièce
        for (int x = 0; x < blocksMainRoom.length; x++) {
            for (int y = 0; y < blocksMainRoom[x].length; y++) {
                if ((x == 0 || x == blocksMainRoom.length - 1 || y == 0 || y == blocksMainRoom[x].length - 1) && blocksMainRoom[x][y] == null) {
                    blocksMainRoom[x][y] = new Wall();
                }
            }
        }

        //fais un minimum d'itération de découpe des pièces
        int pourcentage = 0;
        for (int i = 0; i < NB_ITERATION_MAX; i++) {
            // Pour chaque pièce, on là divise
            if (i >= NB_ITERATION_MIN) {
                pourcentage += POURCENT_DIVIDE;
            }
            int nbRooms = rooms.length;
            for (int j = 0; j < nbRooms; j++) {
                if (pourcentage != 0 && this.random.nextInt(1, 100 + 1) + pourcentage > 50) {
                    continue;
                }
                Room roomDivided = divideRoom(rooms[j]);
                // Si la pièce n'a pas pu être divisé
                if (roomDivided == null) {
                    continue;
                }
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided);
            }
        }

        Stage generatedStage = new Stage(rooms, stageLength, stageWidth);

        // Génération des murs
        List<GeneratorRoom> genRooms = new LinkedList<>();
        List<GeneratorWall> genWalls = new LinkedList<>(); // Contient tout les murs de toutes les pièces
        // On génère les murs des pièces
        for (Room room : rooms) {
            GeneratorRoom genRoom = new GeneratorRoom(room, generatedStage);
            genRooms.add(genRoom);
            genWalls.addAll(genRoom.getWalls());
        }

        // On ajoute les murs qui ont des blocs en communs
        for (GeneratorRoom genRoom : genRooms) {
            // En gros, on prend les murs commun avec les pièces d'a côté
            for (GeneratorWall roomWall : genRoom.getWalls()) {
                for (GeneratorWall wall : genWalls) {
                    if (roomWall.overlapWall(wall) && !genRoom.getWalls().contains(wall)) {
                        genRoom.getSideWalls().add(wall);
                    }
                }
            }
        }
        
        GeneratorRoom playerRoom = genRooms.get(random.nextInt(0, genRooms.size()));

        // On fait un trou dans chaque mur de chaque pièce
        // TODO Faire l'algorithme au lieu de ce truc de test débile
        for (GeneratorRoom genRoom : genRooms) {
            for (GeneratorWall roomWall : genRoom.getWalls()) {
                for (GeneratorWall otherRoomWall : genRoom.getSideWalls()) {
                    if (roomWall.overlapWall(otherRoomWall)) {
                        roomWall.breakWall(otherRoomWall, random);
                    }
                }
            }
        }

        // On met des portes où il y a des passage
        for (GeneratorRoom genRoom : genRooms) {
            for (GeneratorWall roomWall : genRoom.getWalls()) {
                for (Location location : roomWall.getPassages()) {
                    try {
                        generatedStage.setBlock(location.getX(), location.getY(), new Door());
                    } catch (CoordinatesOutOfBoundsException ex) {
                    }
                }
            }
        }

        return generatedStage;
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
     * Méthode permettant de récupérer la seed du jeu.
     *
     * @return seed long correspondant à la graine
     */
    public long getSeed() {
        return seed;
    }
}
