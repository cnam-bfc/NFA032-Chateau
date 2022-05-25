package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.Random;

public class Spider extends Enemy {
    private static final int MIN_HEALTH = 20;
    private static final int MAX_HEALTH = 40;
    private static final int MIN_RESISTANCE = 2;
    private static final int MAX_RESISTANCE = 5;
    private static final int MIN_STRENGTH = 5;
    private static final int MAX_STRENGTH = 10;
    private static final int MIN_ACCURACY = 5;
    private static final int MAX_ACCURACY = 10;
    private static final int MIN_SPEED = 5;
    private static final int MAX_SPEED = 10;

    public Spider(App app, Stage stage, Location location, Random random) {
        super(app, stage, location, "Araignée",
                random.nextInt(MIN_HEALTH, MAX_HEALTH),
                random.nextInt(MIN_RESISTANCE, MAX_RESISTANCE),
                random.nextInt(MIN_STRENGTH, MAX_STRENGTH),
                random.nextInt(MIN_ACCURACY, MAX_ACCURACY),
                random.nextInt(MIN_SPEED, MAX_SPEED));
    }

    public Spider(App app, Stage stage, Location location, String name, int health, int resistance,
                  int strength, int accuracy, int speed) {
        super(app, stage, location, name, health, resistance, strength, accuracy, speed);
    }

    @Override
    public String getCharacter() {
        return "S";
    }
}
