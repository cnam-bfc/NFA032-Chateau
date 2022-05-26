package net.cnam.chateau.weapon;

import java.util.Random;

public class Epee extends Weapon {

    private final int MIN_POWER = 1; // min inclus
    private final int MAX_POWER = 11; // max exlcu
    private final int MIN_SPEED = 1; // min inclus
    private final int MAX_SPEED = 11; // max exlcu
    private final int MIN_ACCURACY = 1; // min inclus
    private final int MAX_ACCURACY = 11; // max exlcu

    public Epee(Random random) {
        super("Épée", "", 0,0,0);

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
            return "Epee te";
        } else if (total < greatWeapon) {
            return "Epee mediocre";
        } else {
            return "Superbe epee";
        }
    }
}
