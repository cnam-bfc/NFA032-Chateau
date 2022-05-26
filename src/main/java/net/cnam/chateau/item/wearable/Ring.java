package net.cnam.chateau.item.wearable;

import java.util.Random;

public class Ring extends Wearable {
    private final int MIN_POWER = 1; // min inclus
    private final int MAX_POWER = 11; // max exlcu
    private final int MIN_SPEED = 1; // min inclus
    private final int MAX_SPEED = 11; // max exlcu
    private final int MIN_ACCURACY = 1; // min inclus
    private final int MAX_ACCURACY = 11; // max exlcu

    public Ring(Random random) {
        super("Bague", "", 0, 0, 0);

        this.setStrength(random.nextInt(MIN_POWER, MAX_POWER));
        this.setSpeed(random.nextInt(MIN_SPEED, MAX_SPEED));
        this.setAccuracy(random.nextInt(MIN_ACCURACY, MAX_ACCURACY));
        this.setDescription(generateDescription());
    }

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