package net.cnam.chateau.item.weapon;

import java.util.Random;

public class Couteau extends Weapon {

    private final int MIN_POWER = 1; // min inclus
    private final int MAX_POWER = 6; // max exlcu
    private final int MIN_SPEED = 5; // min inclus
    private final int MAX_SPEED = 16; // max exlcu
    private final int MIN_ACCURACY = 1; // min inclus
    private final int MAX_ACCURACY = 11; // max exlcu

    /**
     * Constructeur
     * 
     * @param random objet Random permettant de gérer l'aléatoire de l'objet crée.
     */
    public Couteau(Random random) {
        super("Couteau", "", 0, 0, 0);

        this.setStrength(random.nextInt(MIN_POWER, MAX_POWER));
        this.setSpeed(random.nextInt(MIN_SPEED, MAX_SPEED));
        this.setAccuracy(random.nextInt(MIN_ACCURACY, MAX_ACCURACY));
        this.setDescription(generateDescription());
    }

    // Méthode permettant de gérer la description de l'arme en fonction de ses statitistiques.
    private String generateDescription() {
        int total = super.getStrength() + super.getSpeed() + super.getAccuracy();
        int mediumWeapon = (MAX_POWER + MAX_SPEED + MAX_ACCURACY - 3) / 2;
        int greatWeapon = mediumWeapon + ((MAX_POWER + MAX_SPEED + MAX_ACCURACY - 3) / 4);
        if (total < mediumWeapon) {
            return "Couteau de mauvaise qualité";
        } else if (total < greatWeapon) {
            return "Couteau de qualité médiocre";
        } else {
            return "Couteau de très bonne qualité";
        }
    }
}
