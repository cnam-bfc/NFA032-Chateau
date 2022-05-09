package net.cnam.entity.enemy;

import net.cnam.entity.Characteristic;
import net.cnam.entity.LivingEntity;
import net.cnam.object.Location;
import net.cnam.object.weapon.Weapon;

/**
 * Classe d'un ennemi
 */
public abstract class Enemy extends LivingEntity {

    /**
     * Constructeur d'un ennemie sans arme
     *
     * @param characteristics
     * @param location
     */
    public Enemy(Characteristic characteristics, Location location, String nom) {
        super(characteristics, location, nom);
    }

    /**
     * Constructeur d'un ennemie avec arme
     *
     * @param characteristics
     * @param weapon
     * @param location
     */
    public Enemy(Characteristic characteristics, Weapon weapon, Location location, String nom) {
        super(characteristics, weapon, location, nom);
    }

}
