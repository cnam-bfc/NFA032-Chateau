package net.cnam.chateau.item.wearable;

import java.util.Random;

public class Ring extends Wearable {
    private static final int MIN_POWER = 1; // min inclus
    private static final int MAX_POWER = 6; // max exclus
    private static final int MIN_SPEED = 1; // min inclus
    private static final int MAX_SPEED = 11; // max exclus
    private static final int MIN_ACCURACY = 1; // min inclus
    private static final int MAX_ACCURACY = 6; // max exclus

    /**
     * Constructeur
     *
     * @param random objet Random permettant de gérer l'aléatoire de l'objet crée.
     */
    public Ring(Random random) {
        super("Bague", "", 0, 0, 0);

        this.setStrength(random.nextInt(MIN_POWER, MAX_POWER));
        this.setSpeed(random.nextInt(MIN_SPEED, MAX_SPEED));
        this.setAccuracy(random.nextInt(MIN_ACCURACY, MAX_ACCURACY));
        this.setDescription(generateDescription());
    }

    // Méthode permettant de gérer la description de l'arme en fonction de ses statistiques.
    private String generateDescription() {
        int total = super.getStrength() + super.getSpeed() + super.getAccuracy();
        int mediumWearable = (MAX_POWER + MAX_SPEED + MAX_ACCURACY - 3) / 2;
        int greatWearable = mediumWearable + ((MAX_POWER + MAX_SPEED + MAX_ACCURACY - 3) / 4);
        if (total < mediumWearable) {
            return "Bague de mauvaise qualité";
        } else if (total < greatWearable) {
            return "Bague de qualité médiocre";
        } else {
            return "Bague de très bonne qualité";
        }
    }
}