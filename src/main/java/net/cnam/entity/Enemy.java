package net.cnam.entity;

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
     */
    public Enemy(int health, int resistance, Characteristic characteristics, char character) {
        // Weapon = null
        this(null, health, resistance, characteristics, character);
    }

    /**
     * Constructeur
     *
     * @param weapon L'arme de l'ennemi
     * @param health La santé de l'entité vivante
     * @param resistance La résistance de l'entité vivante
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param character Caractère utilisé lors de l'affichage de l'entité
     */
    public Enemy(Weapon weapon, int health, int resistance, Characteristic characteristics, char character) {
        super(health, resistance, characteristics, character);
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
     * Méthode qui permet de définir l'arme possédé par l'ennemi.
     * null est équivalent aux mains nues
     *
     * @param weapon la nouvelle arme
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
