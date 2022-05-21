package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'un ennemi
 */
public abstract class Enemy extends Entity {

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
    public Enemy(Stage stage, Location location, String name, int health, int resistance, int strength, int accuracy, int speed) {
        super(stage, location, name, health, resistance, strength, accuracy, speed);
    }

    /**
     * Constructeur
     *
     * @param stage L'étage où se situe l'ennemi
     * @param location Coordonnées de l'ennemi
     * @param name Le nom de l'ennemi
     */
    public Enemy(Stage stage, Location location, String name) {
        super(stage, location, name);
    }
}
