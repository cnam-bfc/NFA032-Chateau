package net.cnam.entity.enemy;

import net.cnam.entity.Characteristic;
import net.cnam.entity.LivingEntity;
import net.cnam.utils.Location;
import net.cnam.item.weapon.Weapon;

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
    public Enemy(Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
    }

    /**
     * Constructeur d'un ennemie avec arme
     *
     * @param characteristics
     * @param weapon
     * @param location
     */
    public Enemy(Characteristic characteristics, Weapon weapon, String nom, Location location) {
        super(characteristics, weapon, nom, location);
    }

}
