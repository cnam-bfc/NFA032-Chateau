package net.cnam.chateau.item.wearable;

import java.util.Random;

public class Earring extends Wearable {
    private final int MIN_POWER = 1; // min inclus
    private final int MAX_POWER = 6; // max exlcu
    private final int MIN_SPEED = 1; // min inclus
    private final int MAX_SPEED = 6; // max exlcu
    private final int MIN_ACCURACY = 1; // min inclus
    private final int MAX_ACCURACY = 11; // max exlcu

    /**
     * Constructeur
     * 
     * @param random objet Random permettant de gérer l'aléatoire de l'objet crée.
     */
    public Earring(Random random) {
        super("Boucles d'oreilles", "", 0, 0, 0);

        this.setStrength(random.nextInt(MIN_POWER, MAX_POWER));
        this.setSpeed(random.nextInt(MIN_SPEED, MAX_SPEED));
        this.setAccuracy(random.nextInt(MIN_ACCURACY, MAX_ACCURACY));
        this.setDescription(generateDescription());
    }

    // Méthode permettant de gérer la description de l'arme en fonction de ses statitistiques.
    private String generateDescription() {
        int total = super.getStrength() + super.getSpeed() + super.getAccuracy();
        int mediumWearable = (MAX_POWER + MAX_SPEED + MAX_ACCURACY - 3) / 2;
        int greatWearable= mediumWearable + ((MAX_POWER + MAX_SPEED + MAX_ACCURACY - 3) / 4);
        if (total < mediumWearable) {
            return "Boucles d'oreilles de mauvaise qualité";
        } else if (total < greatWearable) {
            return "Boucles d'oreilles de qualité médiocre";
        } else {
            return "Boucles d'oreilles de très bonne qualité";
        }
    }
}