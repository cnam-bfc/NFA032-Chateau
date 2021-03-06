package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.Random;

public class Spider extends Enemy {
    private static final String CHARACTER = "S";
    private static final int MIN_HEALTH = 20;
    private static final int MAX_HEALTH = 40;
    private static final int MIN_STRENGTH = 5;
    private static final int MAX_STRENGTH = 10;
    private static final int MIN_ACCURACY = 20;
    private static final int MAX_ACCURACY = 30;
    private static final int MIN_SPEED = 20;
    private static final int MAX_SPEED = 30;

    /**
     * Constructeur de l'araignée pour faire une entité avec des stats aléatoire.
     * 
     * @param app       L'application
     * @param stage     L'étage où il se situe
     * @param location  Les coordonnées où il se situe
     * @param random    Le random permettant de générer l'aléatoire
     */
    public Spider(App app, Stage stage, Location location, Random random) {
        super(app, stage, location, "Araignée",
                random.nextInt(MIN_HEALTH, MAX_HEALTH),
                random.nextInt(MIN_STRENGTH, MAX_STRENGTH),
                random.nextInt(MIN_ACCURACY, MAX_ACCURACY),
                random.nextInt(MIN_SPEED, MAX_SPEED),
                CHARACTER);
    }

    /**
     * Constructeur de l'araignée pour le générer de façon personnalisée
     *
     * @param app           L'application
     * @param stage         L'étage où il se situe
     * @param location      Les coordonnées où il se situe
     * @param name          Le nom
     * @param health        La vie
     * @param strength      La force
     * @param accuracy      La précision
     * @param speed         La rapidité
     */
    public Spider(App app, Stage stage, Location location, String name, int health,
                  int strength, int accuracy, int speed) {
        super(app, stage, location, name, health, strength, accuracy, speed, CHARACTER);
    }
}
