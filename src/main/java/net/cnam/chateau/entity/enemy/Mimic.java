package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;

import java.util.Random;

public class Mimic extends Enemy {
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

    /**
     * Constructeur du Mimic sans tête pour faire une entité avec des stats aléatoire.
     * 
     * @param app       L'application
     * @param random    Le random permettant de générer l'aléatoire
     */
    public Mimic(App app, Random random) {
        super(app, null, null, "Mimic",
                random.nextInt(MIN_HEALTH, MAX_HEALTH),
                random.nextInt(MIN_RESISTANCE, MAX_RESISTANCE),
                random.nextInt(MIN_STRENGTH, MAX_STRENGTH),
                random.nextInt(MIN_ACCURACY, MAX_ACCURACY),
                random.nextInt(MIN_SPEED, MAX_SPEED)
        );
    }

    /**
     * Redéfinition de la méthode permettant d'afficher l'entité sur la carte.
     * 
     * @return un String "C"
     */
    @Override
    public String getCharacter() {
        return "C";
    }
}
