package net.cnam.entity;

import net.cnam.object.Location;

/**
 * Classe abstraite d'une entité vivante
 */
public abstract class LivingEntity extends Entity {

    private int health;
    private int resistance;
    private Characteristic characteristics;

    /**
     * Constructeur
     *
     * @param health La santé de l'entité vivante
     * @param resistance La résistance de l'entité vivante
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param location Coordonnées de l'entité
     */
    public LivingEntity(int health, int resistance, Characteristic characteristics, Location location) {
        super(location);
        this.health = health;
        this.resistance = resistance;
        this.characteristics = characteristics;
    }

    /**
     * Méthode permettant de récupérer la santé de l'entité
     *
     * @return la santé
     */
    public int getHealth() {
        return health;
    }

    /**
     * Méthode permettant de définir la santé de l'entité
     *
     * @param health la nouvelle santé
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Méthode permettant de récupérer la résistance de l'entité
     *
     * @return la résistance
     */
    public int getResistance() {
        return resistance;
    }

    /**
     * Méthode permettant de définir la résistance de l'entité
     *
     * @param resistance la nouvelle résistance
     */
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    /**
     * Méthode permettant de récupérer les caractérisiques de l'entité
     *
     * @return les caractéristiques
     */
    public Characteristic getCharacteristics() {
        return characteristics;
    }

    /**
     * Méthode permettant de définir les caractéristiques de l'entité
     *
     * @param characteristics les nouvelles caractéristiques
     */
    public void setCharacteristics(Characteristic characteristics) {
        this.characteristics = characteristics;
    }
}
