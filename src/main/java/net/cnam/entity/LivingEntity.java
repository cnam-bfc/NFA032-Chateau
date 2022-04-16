package net.cnam.entity;

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
     * @param character Caractère utilisé lors de l'affichage de l'entité
     */
    public LivingEntity(int health, int resistance, Characteristic characteristics, char character) {
        super(character);
        this.health = health;
        this.resistance = resistance;
        this.characteristics = characteristics;
    }
}
