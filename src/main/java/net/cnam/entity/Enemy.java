package net.cnam.entity;

import net.cnam.object.Location;
import net.cnam.object.Weapon;

/**
 * Classe d'un ennemi
 */
public class Enemy extends LivingEntity {

    private Weapon weapon;

    /**
     * Constructeur
     *
     * @param health La santé de l'entité vivante
     * @param resistance La résistance de l'entité vivante
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param character Caractère utilisé lors de l'affichage de l'entité
     * @param location Coordonnées de l'entité
     */
    public Enemy(int health, int resistance, Characteristic characteristics, char character, Location location) {
        // Weapon = null
        this(null, health, resistance, characteristics, character, location);
    }

    /**
     * Constructeur
     *
     * @param weapon L'arme de l'ennemi
     * @param health La santé de l'entité vivante
     * @param resistance La résistance de l'entité vivante
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param character Caractère utilisé lors de l'affichage de l'entité
     * @param location Coordonnées de l'entité
     */
    public Enemy(Weapon weapon, int health, int resistance, Characteristic characteristics, char character, Location location) {
        super(health, resistance, characteristics, character, location);
        this.weapon = weapon;
    }

    /**
     * Méthode qui permet de récupérer l'arme possédé par l'ennemi
     *
     * @return l'arme
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Méthode qui permet de définir l'arme possédé par l'ennemi. null est
     * équivalent aux mains nues
     *
     * @param weapon la nouvelle arme
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
