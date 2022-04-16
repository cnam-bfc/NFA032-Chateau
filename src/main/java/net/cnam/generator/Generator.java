package net.cnam.generator;

import java.util.Random;
import net.cnam.object.Location;
import net.cnam.structure.*;
import net.cnam.structure.block.Block;
import static net.cnam.utils.array.ArrayUtils.extendArray;

/**
 * Class pour la génération du jeu
 *
 */
public class Generator {

    private static final int MIN_SIZE_STAGE = 70; //taille mini d'un étage
    private static final int MAX_SIZE_STAGE = 80; //taille maxi d'un étage
    private static final int MIN_STAGE = 3; //nombre mini d'étage
    private static final int MAX_STAGE = 5; //nombre maxi d'étage
    private static final int MIN_SIZE_ROOM = 10; //taille mini d'une pièce
    private static final double NB_ITERATION = 3; //nombre de division minimum des étages

    private long seed;
    private Random random;

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
     * Méthode qui permet de définir le chateau.
     *
     * @return un objet Chateau qui correspond à la map du chateau
     */
    public Castle generateCastle() {
        Castle result = new Castle(this.generateStages());
        return result;
    }

    /**
     * Méthode qui permet de définir le nombre d'étage.
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

    public Stage generateStage() {
        Room[] tabRoomStage = new Room[1];
        Room divide = new Room(new Location(0, 0), new Block[this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE)][this.random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE)]);
        tabRoomStage[0] = divide;

        for (int i = 0; i < NB_ITERATION; i++) {
            for (int k = 0; k < tabRoomStage.length; k++) {
                Room result = divideRoom(tabRoomStage[k]);
                if (result == null) {
                    continue;
                }
                tabRoomStage = extendArray(tabRoomStage);
                tabRoomStage[tabRoomStage.length - 1] = result;
            }
        }

        return new Stage(tabRoomStage);

    }

    public Room divideRoom(Room room) {

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
