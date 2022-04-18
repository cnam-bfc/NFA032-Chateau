package net.cnam.generator;

import java.util.Random;
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
    private static final int NB_ITERATION = 3; //nombre de division minimum des étages

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
        Room roomBase = new Room(new Location(0, 0), new Block[stageLength][stageWidth]);
        rooms[0] = roomBase;

        // Pour chaque itération
        for (int i = 0; i < NB_ITERATION; i++) {
            // Pour chaque pièce, on là divise
            int nbRooms = rooms.length;
            for (int j = 0; j < nbRooms; j++) {
                Room roomDivided = divideRoom(rooms[j]);
                // Si la pièce n'a pas pu être divisé
                if (roomDivided == null) {
                    continue;
                }
                rooms = ArrayUtils.addOnBottomOfArray(rooms, roomDivided);

                //TODO Redimentionner l'ancienne room
            }
        }

        return new Stage(rooms, stageLength, stageWidth);
    }

    public Room divideRoom(Room room) {
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
