package net.cnam.chateau.item.weapon;

import java.util.Random;

public class Couteau extends Weapon {

    private final int MIN_POWER = 1; // min inclus
    private final int MAX_POWER = 11; // max exlcu
    private final int MIN_SPEED = 1; // min inclus
    private final int MAX_SPEED = 11; // max exlcu
    private final int MIN_ACCURACY = 1; // min inclus
    private final int MAX_ACCURACY = 11; // max exlcu

    public Couteau(Random random) {
        super("Couteau", "", 0, 0, 0);

        this.setPower(random.nextInt(MIN_POWER, MAX_POWER));
        this.setSpeed(random.nextInt(MIN_SPEED, MAX_SPEED));
        this.setAccuracy(random.nextInt(MIN_ACCURACY, MAX_ACCURACY));
        this.setDescription(generateDescription());
    }

    private String generateDescription() {
        int total = super.getPower() + super.getSpeed() + super.getAccuracy();
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
