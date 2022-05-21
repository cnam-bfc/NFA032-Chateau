package net.cnam.chateau.entity.enemy.boss;

import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public abstract class Boss extends Enemy {

    /**
     * Constructeur
     *
     * @param stage L'étage où il se situe
     * @param location Coordonnées où il se situe
     * @param name Le nom
     * @param health La santé
     * @param resistance La résistance
     * @param strength La force
     * @param accuracy La précision
     * @param speed La rapidité
     */
    public Boss(Stage stage, Location location, String name, int health, int resistance, int strength, int accuracy, int speed) {
        super(stage, location, name, health, resistance, strength, accuracy, speed);
    }
}