package net.cnam.entity;

import net.cnam.object.Weapon;

public class Personage extends LivingEntity {

    private Weapon weapon;
    private String nom;
    private Sexe sexe;

    /**
     * Constructeur
     *
     * @param weapon L'arme du personnage
     * @param nom Le nom du personnage
     * @param sexe Le sexe du personnage
     * @param health La santé de l'entité vivante
     * @param resistance La résistance de l'entité vivante
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param character Caractère utilisé lors de l'affichage de l'entité
     */
    public Personage(Weapon weapon, String nom, Sexe sexe, int health, int resistance, Characteristic characteristics, char character) {
        super(health, resistance, characteristics, character);
        this.weapon = weapon;
        this.nom = nom;
        this.sexe = sexe;
    }
}
