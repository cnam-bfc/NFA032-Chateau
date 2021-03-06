package net.cnam.chateau.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.entity.Pet;
import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.entity.enemy.*;
import net.cnam.chateau.entity.enemy.boss.BossMartinez;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.AttackPotion;
import net.cnam.chateau.item.consumable.HealPotion;
import net.cnam.chateau.item.weapon.*;
import net.cnam.chateau.item.wearable.Earring;
import net.cnam.chateau.item.wearable.Necklace;
import net.cnam.chateau.item.wearable.Ring;
import net.cnam.chateau.item.wearable.Wearable;
import net.cnam.chateau.structure.*;
import net.cnam.chateau.structure.block.*;
import net.cnam.chateau.structure.block.container.Chest;
import net.cnam.chateau.structure.block.container.Wardrobe;
import net.cnam.chateau.structure.block.decorative.Desk;
import net.cnam.chateau.structure.block.decorative.Seat;
import net.cnam.chateau.structure.block.decorative.Table;
import net.cnam.chateau.structure.block.door.Door;
import net.cnam.chateau.structure.block.door.EnemyDoor;
import net.cnam.chateau.structure.block.door.SageDoor;
import net.cnam.chateau.structure.block.door.TrappedDoor;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.array.ArrayUtils;

import java.util.*;

/**
 * Classe pour la génération de la map.
 * Utilise un objet Random permettant d'utiliser une seed afin de retrouver le même château sur des paramètres identiques.
 */
public class Generator {

    private final GeneratorSettings settings;

    private final App app;
    private final long seed;
    private final Random random;

    private final List<Enemy> specialEnemies = new ArrayList<>();
    private final List<Pet> pets = new ArrayList<>();
    private final List<Sage> sages = new ArrayList<>();
    private final List<Weapon> specialWeapons = new ArrayList<>();
    private final List<Wearable> specialWearable = new ArrayList<>();

    private Location playerStartLocation;

    /**
     * Constructeur.
     * On initialisera dans le constructeur les différentes listes des entités spéciales et uniques non aléatoirement crées.
     *
     * @param app      L'application
     * @param game     La partie en cours
     * @param seed     La seed permettant la génération aléatoire de la map
     * @param settings Les paramètres de génération
     */
    public Generator(App app, Game game, long seed, GeneratorSettings settings) {
        this.app = app;
        this.seed = seed;
        this.random = new Random(seed);
        this.settings = settings;

        // Initialisation des familiers
        pets.add(new Pet(app, "Babe", 75, 20, 15, 10));
        pets.add(new Pet(app, "Chat Potté", 50, 15, 25, 25));
        pets.add(new Pet(app, "Idéfix", 60, 15, 10, 10));
        pets.add(new Pet(app, "Winnie", 75, 20, 15, 10));
        pets.add(new Pet(app, "Panpan", 50, 10, 20, 20));
        pets.add(new Pet(app, "Pépé L'oiseau", 50, 10, 20, 20));

        // Initialisation des ennemis spéciaux (mini-boss)
        specialEnemies.add(new Demogorgon(app, null, null, "Chef demogorgon : Demo-Bob", 100, 20, 17, 10));
        specialEnemies.add(new Harpy(app, null, null, "Cheffe harpie : Senga-Eiram", 100, 10, 25, 25));
        specialEnemies.add(new HeadlessKnight(app, null, null, "Chef chevalier sans tete : 720-headshot", 100, 15, 20, 15));
        specialEnemies.add(new Morbol(app, null, null, "Chef morbol : Gilou", 100, 25, 10, 5));
        specialEnemies.add(new Spider(app, null, null, "Chef araignée : Aragog", 100, 7, 30, 25));
        specialEnemies.add(new Werewolf(app, null, null, "Cheffe loup-garou : Aela", 100, 20, 15, 10));
        specialEnemies.add(new Zombie(app, null, null, "Chef zombie : Maxime", 100, 15, 15, 15));

        // Initialisation des sages
        sages.add(new Sage(app, "Dumbledore", game.getRandomPuzzle(), 75, 20, 10, 10));
        sages.add(new Sage(app, "Merlin", game.getRandomPuzzle(), 100, 15, 15, 10));
        sages.add(new Sage(app, "Kristoff", game.getRandomPuzzle(), 75, 20, 15, 10));
        sages.add(new Sage(app, "Sage : Ither", game.getRandomPuzzle(), 75, 10, 20, 20));
        sages.add(new Sage(app, "Salomon", game.getRandomPuzzle(), 100, 10, 15, 15));
        sages.add(new Sage(app, "Pressea", game.getRandomPuzzle(), 75, 15, 15, 10));
        sages.add(new Sage(app, "Jamy", game.getRandomPuzzle(), 75, 10, 20, 20));
        sages.add(new Sage(app, "Sardoche", game.getRandomPuzzle(), 100, 20, 20, 15));

        // Initialisation des armes spéciales
        specialWeapons.add((new Weapon("La grosse louche", "Louche divine léchée par Etchebest lui même", 15, 10, 5)));
        specialWeapons.add((new Weapon("Frostmourne", "Une épée diabolique qui semble très puissante", 15, 5, 10)));
        specialWeapons.add((new Weapon("Masse de Molag Bal", "Une masse tout droit venu de Bordeciel !", 20, 5, 10)));
        specialWeapons.add((new Weapon("Clavier", "C'est des touches mécaniques", 10, 5, 10)));
        specialWeapons.add((new Weapon("Mjolnir", "C'est pas le vrai mais il fait tout aussi mal", 20, 5, 10)));
        specialWeapons.add((new Weapon("Excalibur", "Elle est enfin sortie de la roche !", 15, 10, 15)));
        specialWeapons.add((new Weapon("Hache Leviathan", "Une hache précieuse et très aiguisée", 10, 10, 10)));

        // Initialisation des objets "portables" (bijoux) spéciaux
        specialWearable.add((new Wearable("Collier de S.P.B", "Collier arborant la formule de l’astrophysicien Karl Schwarzschild", 13, 5, 5)));
        specialWearable.add((new Wearable("Anneau de Sauron", "On y distingue des runes elfiques gravées sur l'anneau", 5, 5, 13)));
        specialWearable.add((new Wearable("Boucles d'oreilles Hanafuda", "Portées de pair en fils par des utilisateurs de katana", 5, 13, 5)));
    }

    /**
     * Méthode qui génère un chateau de A à Z.
     * Appelle la méthode qui génère des étages
     *
     * @return un Château
     */
    public Castle generateCastle() {
        return new Castle(this.generateStages(), this.playerStartLocation, seed);
    }

    /**
     * Méthode qui génère des étages finis.
     *
     * @return un tableau d'étage qui composera le chateau (salle de boss non comprise)
     */
    public Stage[] generateStages() {
        Stage[] stages = new Stage[this.random.nextInt(settings.getMinStage(), settings.getMaxStage() + 1)];

        //On génère chaque étage
        for (int i = 0; i < stages.length; i++) {
            stages[i] = generateStage();
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
            UpStair exitStair = GUtils.triTopo(app, firstRoom, gRooms, random);

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

                // On regarde ou on peut placer l'escalier d'entrée de l'étage et on récupère les coordonnées
                Location location = GUtils.findPosition(this.random, firstRoom);
                int x = location.getX();
                int y = location.getY();

                //on ajoute l'escalier d'entrée dans la pièce du nouvel étage
                firstRoom.getBlocks()[x][y] = entryStair;
                //on récupère la location dans la pièce de l'escalier et on lui ajoute dans ses champs
                entryStair.setLocation(new Location(firstRoom.getLocation().getX() + x, firstRoom.getLocation().getY() + y));

            }
            // On ajoute au champ de l'escalier de sortie de l'étage actuellement traité, l'étage en question auquel il appartient
            // également on ferme la pièce et on cache une clé dans une des pièces antérieure
            exitStair.setStage(stage);

            // Si on arrive au dernier étage, on ajoute un étage supplémentaire pour accueillir la pièce du boss
            if (i == stages.length - 1) {
                RoomBoss lastRoom = new RoomBoss();
                Stage stageBoss = new Stage(new Room[]{lastRoom}, lastRoom.getLength(), lastRoom.getHeight());
                lastRoom.getEntry().setOtherStair(exitStair);
                lastRoom.getEntry().setStage(stageBoss);
                exitStair.setOtherStair(lastRoom.getEntry());
                stageBoss.getEntities().add(new BossMartinez(app, stageBoss, new Location(6, 4)));
            }
        }

        for (Stage stage : stages) {
            for (Room room : stage.getRooms()) {
                generateRoom(room);
            }
        }

        return stages;
    }

    /**
     * Méthode qui génère un étage.
     * On procède dans la méthode à la découpe et l'ajout d'ennemie..
     *
     * @return un étage
     */
    public Stage generateStage() {
        // On défini la taille que fera l'étage entre 2 bornes (constantes)
        int stageLength = this.random.nextInt(settings.getMinSizeStage(), settings.getMaxSizeStage() + 1);
        int stageHeight = this.random.nextInt(settings.getMinSizeStage(), settings.getMaxSizeStage() + 1);

        // On crée un tableau contenant la room de base permettant la découpe de l'étage
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
        for (int i = 0; i < settings.getNbIterationMax(); i++) {
            // À chaque tour de boucle, si on a fait le nombre d'itérations minimum, on ajoute un pourcentage de division
            // Le pourcentage sert à définir si oui ou non on tente de diviser la pièce
            if (i >= settings.getNbIterationMin()) {
                pourcentage += settings.getPourcentDivide();
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
                // Si la pièce a été divisée, on ajoute la nouvelle pièce au tableau
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided);
            }
        }
        rooms = verifyRoomSize(rooms);

        Stage stage = new Stage(rooms, stageLength, stageHeight);

        int nbEntities = this.random.nextInt(settings.getMinEnemiesStage(), settings.getMaxEnemiesStage() + 1);
        // On génère les entités
        for (int i = 0; i < nbEntities; i++) {
            Room room = stage.getRooms()[this.random.nextInt(stage.getRooms().length)];
            Location emptyLocation = GUtils.findPosition(random, room);
            Location enemyLocation = new Location(room.getLocation().getX() + emptyLocation.getX(), room.getLocation().getY() + emptyLocation.getY());
            Enemy enemy = getRandomisedEnemy(stage, enemyLocation);
            stage.getEntities().add(enemy);
        }

        return stage;
    }

    /**
     * Méthode permettant de vérifier que les pièces ne sont pas trop grande.
     * Si une pièce est trop grande, on la divise.
     *
     * @param rooms tableau de pièce d'un étage
     */
    public Room[] verifyRoomSize(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            while (rooms[i].getLength() > settings.getMaxSizeRoom() || rooms[i].getHeight() > settings.getMaxSizeRoom()) {
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
     * Méthode permettant de définir le sens de découpe.
     * Découpe dans le sens où la pièce est la plus grande.
     * Si longueur = largeur alors on procède à une découpe dans un sens aléatoire.
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
     * @return roomRight la pièce de droite
     */
    public Room divideRoomLength(Room roomLeft) {
        if (roomLeft.getLength() / 2 <= settings.getMinSizeRoom()) {
            return null;
        }
        int cut = random.nextInt(settings.getMinSizeRoom(), roomLeft.getLength() - settings.getMinSizeRoom() + 1);

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
        if (roomTop.getHeight() / 2 <= settings.getMinSizeRoom()) {
            return null;
        }
        int cut = random.nextInt(settings.getMinSizeRoom(), roomTop.getHeight() - settings.getMinSizeRoom() + 1);

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
        int numberBlocks = random.nextInt(settings.getMinBlocks(), settings.getMaxBlocks() + 1);
        // On ajoute dans la pièce le nombre de blocs défini au-dessus
        for (int i = 0; i < numberBlocks; i++) {
            Location location = GUtils.findPosition(this.random, room);
            room.getBlocks()[location.getX()][location.getY()] = pickRandomBlock();
        }
    }

    /**
     * Méthode pour placer le player dans la première room de façon aléatoire.
     *
     * @param room room de départ
     * @return La location dans l'étage
     */
    public Location getDefaultPlayerLocation(Room room) {
        Location location = GUtils.findPosition(this.random, room);
        return new Location(room.getLocation().getX() + location.getX(), room.getLocation().getY() + location.getY());
    }

    private List<GRoom> generateStageWalls(Stage generatedStage) {
        // Génération des murs
        List<GRoom> rooms = new LinkedList<>();
        List<GRoomWall> roomsWalls = new LinkedList<>(); // Contient tous les murs de toutes les pièces
        // On génère les murs des pièces
        int nb = 0;
        for (Room room : generatedStage.getRooms()) {
            GRoom genRoom = new GRoom(room, generatedStage, ++nb);
            rooms.add(genRoom);
            roomsWalls.addAll(genRoom.getWalls());
        }

        // On vérifie pour tous les murs lesquels se superposent
        // Pour toutes les pièces
        for (GRoom room : rooms) {
            // On boucle sur tout lees murs de la pièce (room)
            for (GRoomWall wall : room.getWalls()) {
                // On regarde dans la liste qui contient tous les murs s'il y a un mur qui se superpose avec ce mur (wall)
                for (GRoomWall tempWall : roomsWalls) {
                    // On vérifie aussi que le mur (wall) n'est pas celui qui resort de la liste totale des murs
                    if (wall != tempWall && wall.overlapWall(tempWall)) {
                        // On ajoute (tempWall) à la liste des murs qui se superpose sur wall
                        wall.getSideWalls().add(tempWall);
                    }
                }
            }
        }

        // On crée les murs individuels
        List<GWall> walls = new ArrayList<>(); // Contient tous les murs
        // Pour toutes les pièces
        for (GRoom room : rooms) {
            // On boucle sur tous les murs de la pièce (room)
            for (GRoomWall wall : room.getWalls()) {
                // On regarde dans la liste qui contient tous les murs s'il y a un mur qui se superpose avec ce mur (wall)
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
        List<GWall> wallsUnBroken = new ArrayList<>();
        for (GWall wall : walls) {
            if (!wall.isBroken()) {
                wallsUnBroken.add(wall);
            }
        }

        // On fait des trous dans des murs au hasard
        for (int i = 0; i < random.nextInt(wallsUnBroken.size() / 6, wallsUnBroken.size() / 3 + 1); i++) {
            breakWall(generatedStage, wallsUnBroken.remove(random.nextInt(wallsUnBroken.size())));
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
            stage.setBlock(breakPoint.getX(), breakPoint.getY(), getRandomDoor(stage, wall.getRoomOne().getRoom(), wall.getRoomTwo().getRoom()));
        } catch (CoordinatesOutOfBoundsException ignored) {
        }
    }

    /**
     * Méthode pour choisir le bloc à placer dans la pièce.
     *
     * @return renvoie un block aléatoire
     */
    public Block pickRandomBlock() {
        if (random.nextInt(1, 101) < settings.getLuckBlock()) {
            switch (random.nextInt(1, 6)) {
                case 1 -> {
                    Chest chest = new Chest(app);
                    if (random.nextInt(1, 101) < 41) {
                        if (random.nextBoolean()) {
                            chest.setHiddenItem(getItem());
                        } else {
                            chest.setHiddenItem(getWeapon());
                        }
                    }
                    return chest;
                }
                case 2 -> {
                    Wardrobe wardrobe = new Wardrobe(app);
                    if (random.nextInt(1, 101) < 26) {
                        if (random.nextInt(0, 3) < 2) {
                            wardrobe.setHiddenItem(getItem());
                        } else {
                            wardrobe.setHiddenItem(getWeapon());
                        }
                    }
                    return wardrobe;
                }
                case 3 -> {
                    return new Bed(app);
                }
                case 4 -> {
                    if (random.nextInt(0, 4) < 3) {
                        return new Cage(app, getRandomPet());
                    } else {
                        return new Cage(app);
                    }
                }
                case 5 -> {
                    TrappedChest trappedChest = new TrappedChest(app, random);
                    trappedChest.getEnemy().setItem(getItem());
                    if (random.nextBoolean()) {
                        trappedChest.getEnemy().setWeapon(getWeapon());
                    }
                    return trappedChest;
                }
            }
        } else {
            switch (random.nextInt(1, 4)) {
                case 1 -> {
                    return new Seat();
                }
                case 2 -> {
                    return new Table();
                }
                case 3 -> {
                    return new Desk();
                }
            }
        }
        return null;
    }

    /**
     * Méthode qui permet de piocher un familier aléatoire dans la liste initialisée.
     * Si plus de familier dans la liste, renvoie null.
     *
     * @return
     */
    private Pet getRandomPet() {
        if (pets.isEmpty()) {
            return null;
        }

        return pets.remove(random.nextInt(0, pets.size()));
    }

    /**
     * Méthode qui permet de retourner un ennemi aléatoire entre la liste d'ennemi spéciaux et des ennemis aléatoires.
     *
     * @param stage    L'étage
     * @param location La position où l'ennemi doit être créé
     * @return L'ennemi
     */
    public Enemy getRandomEnemy(Stage stage, Location location) {
        if (random.nextInt(1, 101) < settings.getLuckSpecialEnemy() && !specialEnemies.isEmpty()) {
            return getSpecialEnemy(stage, location);
        } else {
            return getRandomisedEnemy(stage, location);
        }
    }

    /**
     * Méthode qui permet de retourner un ennemi spécial aléatoire entre la liste d'ennemi spéciaux.
     * Si la liste d'ennemi spécial est vide, renvoie un ennemi aléatoirement généré.
     *
     * @return L'ennemi
     */
    public Enemy getSpecialEnemy(Stage stage, Location location) {
        if (specialEnemies.isEmpty()) {
            return getRandomisedEnemy(stage, location);
        }
        return specialEnemies.remove(random.nextInt(0, specialEnemies.size()));
    }

    /**
     * Méthode qui génère un ennemi aléatoire.
     * L'ennemi peut potentiellement porter un objet ou une arme.
     *
     * @param stage    L'étage
     * @param location La position où l'ennemi doit être créé
     * @return L'ennemi
     */
    public Enemy getRandomisedEnemy(Stage stage, Location location) {
        Enemy entity = null;
        switch (random.nextInt(0, 7)) {
            case 0 -> {
                entity = new Demogorgon(app, stage, location, random);
                if (random.nextInt(0, 3) < 1) {
                    entity.setWeapon(getWeapon());
                }
                if (random.nextInt(0, 3) < 2) {
                    entity.setItem(getItem());
                }
            }
            case 1 -> {
                entity = new Harpy(app, stage, location, random);
                entity.setItem(getItem());
                if (random.nextInt(0, 3) < 1) {
                    entity.setWeapon(getWeapon());
                }
            }
            case 2 -> {
                entity = new HeadlessKnight(app, stage, location, random);
                if (random.nextInt(0, 3) < 2) {
                    entity.setItem(getItem());
                }
                if (random.nextInt(0, 4) < 3) {
                    entity.setWeapon(getWeapon());
                }
            }
            case 3 -> {
                entity = new Morbol(app, stage, location, random);
                if (random.nextInt(0, 3) < 2) {
                    entity.setItem(getItem());
                }
            }
            case 4 -> entity = new Spider(app, stage, location, random);
            case 5 -> {
                entity = new Werewolf(app, stage, location, random);
                if (random.nextInt(0, 3) < 2) {
                    entity.setItem(getItem());
                }
            }
            case 6 -> {
                entity = new Zombie(app, stage, location, random);
                if (random.nextInt(0, 3) < 1) {
                    entity.setItem(getItem());
                }
            }
        }
        return entity;
    }

    /**
     * Méthode qui permet de retourner une arme soit spéciale soit aléatoire.
     *
     * @return L'arme
     */
    public Weapon getWeapon() {
        int random = this.random.nextInt(1, 101);
        if (random <= 10 && !specialWeapons.isEmpty()) {
            return getRandomSpecialWeapon();
        } else {
            return getRandomWeapon();
        }
    }

    /**
     * Méthode qui permet de retourner une arme généré de façon aléatoire.
     *
     * @return L'arme
     */
    public Weapon getRandomWeapon() {
        Weapon weapon = null;
        switch (random.nextInt(0, 4)) {
            case (0) -> weapon = new Couteau(random);
            case (1) -> weapon = new Epee(random);
            case (2) -> weapon = new Hache(random);
            case (3) -> weapon = new Massue(random);
        }
        return weapon;
    }

    /**
     * Méthode qui permet de retourner une arme spéciale.
     * Si la liste d'arme spéciale est vide, renvoie une arme aléatoire.
     *
     * @return L'arme
     */
    public Weapon getRandomSpecialWeapon() {
        if (!specialWeapons.isEmpty()) {
            return specialWeapons.remove(random.nextInt(0, specialWeapons.size()));
        }
        return getRandomWeapon();
    }

    /**
     * Méthode qui permet de retourner un objet soit spécial soit aléatoire.
     *
     * @return L'objet
     */
    public Item getItem() {
        int random = this.random.nextInt(1, 101);
        if (random <= 10 && !specialWearable.isEmpty()) {
            return getRandomSpecialWearableItem();
        } else {
            return getRandomItem();
        }
    }

    /**
     * Méthode qui permet de retourner un objet généré de façon aléatoire.
     *
     * @return L'objet
     */
    public Item getRandomItem() {
        Item item = null;
        if (random.nextInt(0, 4) < 3) {
            switch (random.nextInt(0, 2)) {
                case (0) -> item = new HealPotion(random);
                case (1) -> item = new AttackPotion(random);
            }
        } else {
            switch (random.nextInt(0, 3)) {
                case (0) -> item = new Earring(random);
                case (1) -> item = new Necklace(random);
                case (2) -> item = new Ring(random);
            }
        }
        return item;
    }

    /**
     * Méthode qui permet de retourner un objet spécial.
     *
     * @return L'objet
     */
    public Item getRandomSpecialWearableItem() {
        if (!specialWearable.isEmpty()) {
            return specialWearable.remove(random.nextInt(0, specialWearable.size()));
        }
        return getRandomItem();
    }

    /**
     * Méthode qui permet de retourner un sage aléatoire provenant de la liste.
     * Retourne null si la liste est vide.
     *
     * @return Le sage
     */
    private Sage getRandomSage() {
        if (sages.isEmpty()) {
            return null;
        }

        return sages.remove(random.nextInt(0, sages.size()));
    }

    /**
     * Méthode qui permet de retourner une porte aléatoire.
     * Si la liste de sage est vide, renvoie une EnemyDoor.
     *
     * @param stage     L'étage
     * @param roomOne   La première pièce
     * @param roomTwo   La deuxième pièce
     * @return La porte
     */
    private Door getRandomDoor(Stage stage, Room roomOne, Room roomTwo) {
        int randomInt = random.nextInt(1, 101);
        if (randomInt < 70) {
            return new Door(stage, roomOne, roomTwo);
        } else if (randomInt < 80) {
            if (!sages.isEmpty()) {
                return new SageDoor(app, stage, roomOne, roomTwo, getRandomSage());
            } else {
                return new EnemyDoor(stage, roomOne, roomTwo, getRandomEnemy(null, null));
            }
        } else if (randomInt < 90) {
            return new TrappedDoor(app, stage, roomOne, roomTwo, random);
        } else {
            return new EnemyDoor(stage, roomOne, roomTwo, getRandomEnemy(null, null));
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



