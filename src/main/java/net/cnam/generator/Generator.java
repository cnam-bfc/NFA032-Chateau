package net.cnam.generator;

import java.util.Random;
import net.cnam.object.Couple;
import net.cnam.object.Location;
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
        return new Castle(this.generateStages());
    }

    /**
     * Méthode qui génère des étages. Appelle la méthode qui génère un étage
     *
     * @return un tableau d'étage
     */
    public Stage[] generateStages() {
        Stage[] result = new Stage[this.random.nextInt(MIN_STAGE, MAX_STAGE)];

        for (int i = 0; i < result.length; i++) {
            Stage stage = generateStage();
            for (Room room : stage.getRooms()) {
                generateRoom(room);
            }
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
        int stageLength = this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE);
        int stageWidth = this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE);

        Room[] rooms = new Room[1];
        rooms[0] = new Room(new Location(0, 0), new Block[stageLength][stageWidth]); //room de base
        generateRoomBorder(rooms[0]); //création de la bordure de la room de base

        //fais un minimum d'itération de découpe des pièces
        int pourcentage = 0;
        for (int i = 0; i < NB_ITERATION_MAX; i++) {
            // Pour chaque pièce, on là divise
            if (i >= NB_ITERATION_MIN) {
                pourcentage += POURCENT_DIVIDE;
            }
            int nbRooms = rooms.length;
            for (int j = 0; j < nbRooms; j++) {
                if (pourcentage != 0 && this.random.nextInt(1, 100) + pourcentage > 50) {
                    continue;
                }
                Couple<Room, Room> roomDivided = divideRoom(rooms[j]);
                // Si la pièce n'a pas pu être divisé
                if (roomDivided == null) {
                    continue;
                }
                rooms[j] = roomDivided.getElemOne();
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided.getElemTwo());
            }
        }

        return new Stage(rooms, stageLength, stageWidth);
    }

    /**
     * Méthode permettant de définir le sens de découpe. Découpe dans le sens ou
     * la pièce est la plus longue Si longueur = largeur : découpe random
     *
     * @param room la pièce à découper
     * @return un couple de pièce résultant de la pièce passé en paramètre
     */
    public Couple<Room, Room> divideRoom(Room room) {
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
     * @param roomLeft la pièce à diviser
     * @return un couple de pièce résultant de la découpe de la pièce passé en
     * paramètre
     */
    public Couple<Room, Room> divideRoomLength(Room roomLeft) {
        if (roomLeft.getLength() / 2 <= MIN_SIZE_ROOM) {
            return null;
        }
        int cut = random.nextInt(MIN_SIZE_ROOM, roomLeft.getLength() - MIN_SIZE_ROOM);

        Block[][] tabRoomRight = new Block[roomLeft.getLength() - cut + 1][roomLeft.getHeight()];
        Block[][] tabRoomTransition = roomLeft.getBlocks();
        Block[][] tabRoomLeft = new Block[roomLeft.getLength() - tabRoomRight.length + 1][tabRoomRight[0].length];

        for (int x = 0; x < tabRoomTransition.length; x++) {
            for (int y = 0; y < tabRoomTransition[0].length; y++) {
                if (x < tabRoomTransition.length - cut) {
                    tabRoomLeft[x][y] = tabRoomTransition[x][y];
                } else if (x > tabRoomTransition.length - cut) {
                    tabRoomRight[x - tabRoomRight.length][y] = tabRoomTransition[x][y];
                } else {
                    tabRoomRight[x - tabRoomRight.length][y] = tabRoomLeft[x][y] = tabRoomTransition[x][y];
                }
            }
        }

        Room roomRight = new Room(new Location(roomLeft.getLocation().getX() + cut - 1, roomLeft.getLocation().getY()), tabRoomRight);
        roomLeft.setBlocks(tabRoomLeft);

        generateRoomBorder(roomLeft);
        generateRoomBorder(roomRight);

        return new Couple<>(roomLeft, roomRight);
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
    public Couple<Room, Room> divideRoomWidth(Room roomTop) {
        if (roomTop.getHeight() / 2 <= MIN_SIZE_ROOM) {
            return null;
        }
        int cut = random.nextInt(MIN_SIZE_ROOM, roomTop.getHeight() - MIN_SIZE_ROOM);
        
        Block[][] tabRoomBot = new Block[roomTop.getLength()][roomTop.getHeight() - cut + 1];
        Block[][] tabRoomTransition = roomTop.getBlocks();
        Block[][] tabRoomTop = new Block[tabRoomBot.length][roomTop.getHeight() - tabRoomBot[0].length + 1];
        
        for (int x = 0; x < tabRoomTransition.length; x++) {
            for (int y = 0; y < tabRoomTransition[0].length; y++) {
                if (y < tabRoomTransition[0].length - cut) {
                    tabRoomTop[x][y] = tabRoomTransition[x][y];
                } else if (y > tabRoomTransition[0].length - cut) {
                    tabRoomBot[x][y - tabRoomBot.length] = tabRoomTransition[x][y];
                } else {
                    tabRoomBot[x][y - tabRoomBot.length] = tabRoomTop[x][y] = tabRoomTransition[x][y];
                }
            }
        }
        
        Room roomBot = new Room(new Location(roomTop.getLocation().getX(), roomTop.getLocation().getY() + cut - 1), tabRoomBot);
        roomTop.setBlocks(tabRoomTop);
        
        generateRoomBorder(roomTop);
        generateRoomBorder(roomBot);
        
        return new Couple<>(roomTop, roomBot);
    }

    public void generateRoom(Room room) {
        // populateRoom(room);
    }

    public void generateRoomBorder(Room room) {
        Block[][] tabBlock = room.getBlocks();

        for (int x = 0; x < tabBlock.length; x++) {
            for (int y = 0; y < tabBlock[x].length; y++) {
                if ((x == 0 || x == tabBlock.length - 1 || y == 0 || y == tabBlock[x].length - 1) && tabBlock[x][y] == null) {
                    tabBlock[x][y] = new Wall();
                }
            }
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
