package net.cnam.generator;

import java.util.Random;
import net.cnam.object.Couple;
import net.cnam.object.Location;
import net.cnam.structure.*;
import net.cnam.structure.block.Block;
import net.cnam.utils.array.ArrayUtils;

/**
 * Classe pour la génération de la map
 */
public class Generator {

    private static final int MIN_SIZE_STAGE = 70; //taille mini d'un étage
    private static final int MAX_SIZE_STAGE = 80; //taille maxi d'un étage
    private static final int MIN_STAGE = 3; //nombre mini d'étage
    private static final int MAX_STAGE = 5; //nombre maxi d'étage
    private static final int MIN_SIZE_ROOM = 10; //taille mini d'une pièce
    private static final int NB_ITERATION_MIN = 3; //nombre de division minimum des étages
    private static final int NB_ITERATION_MAX = 5; //nombre de division maximum supplémentaire des étages
    private static final int CHOICE_DIVIDE = 35; //ajusteur de choix de la division hauteur/longeur
    private static final int POURCENT_DIVIDE = 10; //ajusteur pour savoir si une pièce se re divise dans la deuxième phase de division

    private final long seed;
    private final Random random;

    /**
     * Constructeur
     *
     * @param seed long qui permet de générer la carte de façon procédural
     */
    public Generator(long seed) { //une seed bugé : 2027015466020144793
        this.seed = seed;
        this.random = new Random(seed);
    }

    /**
     * Méthode qui génère un chateau.
     *
     * @return un Chateau
     */
    public Castle generateCastle() {
        return new Castle(this.generateStages());
    }

    /**
     * Méthode qui génère des étages.
     *
     * @return un tableau d'étage
     */
    public Stage[] generateStages() {
        Stage[] result = new Stage[this.random.nextInt(MIN_STAGE, MAX_STAGE)];

        for (int i = 0; i < result.length; i++) {
            result[i] = generateStage();
        }

        return result; //return generateRooms(result);
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
        rooms[0] = new Room(new Location(0, 0), new Block[stageLength][stageWidth]); //room base

        //fais un minimum d'itération de découpe des pièces
        int pourcentage = 0 ;
        for (int i = 0; i < NB_ITERATION_MAX; i++) {
            // Pour chaque pièce, on là divise
            if (i >= NB_ITERATION_MIN) pourcentage += POURCENT_DIVIDE;
            int nbRooms = rooms.length;
            for (int j = 0; j < nbRooms; j++) {
                if (pourcentage != 0 && this.random.nextInt(1 , 100) + pourcentage > 50) continue;
                Couple<Room, Room> roomDivided = divideRoom(rooms[j]);
                // Si la pièce n'a pas pu être divisé
                if (roomDivided == null) {
                    continue;
                }
                rooms[j] = roomDivided.getElemOne();
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided.getElemTwo());

                //TODO Redimentionner l'ancienne room
            }
        }

        return new Stage(rooms, stageLength, stageWidth);
    }

    /**
     * Méthode permettant de définir le sens de découpe.
     *
     * @param room la pièce à découper
     * @return un couple de pièce résultant de la pièce passé en paramètre
     */
    public Couple<Room, Room> divideRoom(Room room) {

        int chooseDivideModificator;
        if (room.getLength() < room.getWidth()) {
            chooseDivideModificator = CHOICE_DIVIDE;
        } else if (room.getLength() > room.getWidth()) {
            chooseDivideModificator = -CHOICE_DIVIDE;
        } else {
            chooseDivideModificator = 0;
        }
        
        if (this.random.nextInt(1,100) + chooseDivideModificator > 50) {
            System.out.println("longueur"); //test
            return divideRoomLength(room);
        }
        System.out.println("largeur"); //test
        return divideRoomWidth(room);
    }

    /**
     * Méthode pour diviser une pièce en deux pièces en découpant en longueur.
     * Si renvoie null c'est que la taille de la pièce n'est pas suffisante pour
     * être divisé
     *
     * @param room la pièce à diviser
     * @return un couple de pièce résultant de la découpe de la pièce passé en
     * paramètre
     */
    public Couple<Room, Room> divideRoomLength(Room room) {
        if (room.getLength() / 2 <= MIN_SIZE_ROOM) {
            return null;
        }
        int cut = random.nextInt(MIN_SIZE_ROOM, room.getLength() - MIN_SIZE_ROOM) + 1; //+1 pour le mur entre les deux pièces
        Block[][] block = room.getBlocks();
        Room newRoom = new Room(new Location(room.getLocation().getX() + cut, room.getLocation().getY()), new Block[block.length - cut + 1][block[0].length]);
        room.setBlocks(new Block[cut][block[0].length]);
        return new Couple<>(room, newRoom);
    }

    /**
     * Méthode pour diviser une pièce en deux pièces en découpant en largeur. Si
     * renvoie null c'est que la taille de la pièce n'est pas suffisante pour
     * être divisé
     *
     * @param room la pièce à diviser
     * @return un couple de pièce résultant de la découpe de la pièce passé en
     * paramètre
     */
    public Couple<Room, Room> divideRoomWidth(Room room) {
        if (room.getWidth() / 2 <= MIN_SIZE_ROOM) {
            return null;
        }
        int cut = random.nextInt(MIN_SIZE_ROOM, room.getWidth() - MIN_SIZE_ROOM) + 1;
        Block[][] block = room.getBlocks();
        Room newRoom = new Room(new Location(room.getLocation().getX(), room.getLocation().getY() + cut), new Block[block.length][block[0].length - cut + 1]);
        room.setBlocks(new Block[block.length][cut]);
        return new Couple<>(room, newRoom);
    }
    
    public Stage[] generateRooms (Stage[] Stage){
        return null;
    }
    
    public Stage[] generateRoomsBorder (Stage[] Stage){
        return null;
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
