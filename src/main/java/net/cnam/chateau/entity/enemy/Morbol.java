package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.Random;

public class Morbol extends Enemy {
    private static final String CHARACTER = "M";
    private static final int MIN_HEALTH = 50;
    private static final int MAX_HEALTH = 70;
    private static final int MIN_STRENGTH = 15;
    private static final int MAX_STRENGTH = 30;
    private static final int MIN_ACCURACY = 10;
    private static final int MAX_ACCURACY = 15;
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 4;

    /**
     * Constructeur du Morbol pour faire une entité avec des stats aléatoire.
     *
     * @param app      L'application
     * @param stage    L'étage où il se situe
     * @param location Les coordonnées où il se situe
     * @param random   Le random permettant de générer l'aléatoire
     */
    public Morbol(App app, Stage stage, Location location, Random random) {
        super(app, stage, location, "Morbol",
                random.nextInt(MIN_HEALTH, MAX_HEALTH),
                random.nextInt(MIN_STRENGTH, MAX_STRENGTH),
                random.nextInt(MIN_ACCURACY, MAX_ACCURACY),
                random.nextInt(MIN_SPEED, MAX_SPEED),
                CHARACTER);
    }

    /**
     * Constructeur du Morbol pour le générer de façon personnalisée
     *
     * @param app        L'application
     * @param stage      L'étage où il se situe
     * @param location   Les coordonnées où il se situe
     * @param name       Le nom
     * @param health     La vie
     * @param strength   La force
     * @param accuracy   La précision
     * @param speed      La rapidité
     */
    public Morbol(App app, Stage stage, Location location, String name, int health,
                  int strength, int accuracy, int speed) {
        super(app, stage, location, name, health, strength, accuracy, speed, CHARACTER);
    }
}
