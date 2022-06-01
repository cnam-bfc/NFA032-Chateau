package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.Random;

public class Werewolf extends Enemy {
    private static final String CHARACTER = "W";
    private static final int MIN_HEALTH = 30;
    private static final int MAX_HEALTH = 65;
    private static final int MIN_STRENGTH = 10;
    private static final int MAX_STRENGTH = 15;
    private static final int MIN_ACCURACY = 7;
    private static final int MAX_ACCURACY = 10;
    private static final int MIN_SPEED = 7;
    private static final int MAX_SPEED = 20;

    /**
     * Constructeur du Loup-Garou pour faire une entité avec des stats aléatoire.
     *
     * @param app      L'application
     * @param stage    L'étage où il se situe
     * @param location Les coordonnées où il se situe
     * @param random   Le random permettant de générer l'aléatoire
     */
    public Werewolf(App app, Stage stage, Location location, Random random) {
        super(app, stage, location, "Loup-garou",
                random.nextInt(MIN_HEALTH, MAX_HEALTH),
                random.nextInt(MIN_STRENGTH, MAX_STRENGTH),
                random.nextInt(MIN_ACCURACY, MAX_ACCURACY),
                random.nextInt(MIN_SPEED, MAX_SPEED),
                CHARACTER);
    }

    /**
     * Constructeur du Loup-Garou pour le générer de façon personnalisée
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
    public Werewolf(App app, Stage stage, Location location, String name, int health,
                    int strength, int accuracy, int speed) {
        super(app, stage, location, name, health, strength, accuracy, speed, CHARACTER);
    }
}
