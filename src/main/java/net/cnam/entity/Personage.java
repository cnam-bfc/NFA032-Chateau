package net.cnam.entity;

import net.cnam.object.Location;
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
     * @param location Coordonnées de l'entité
     */
    public Personage(Weapon weapon, String nom, Sexe sexe, int health, int resistance, Characteristic characteristics, char character, Location location) {
        super(health, resistance, characteristics, character, location);
        this.weapon = weapon;
        this.nom = nom;
        this.sexe = sexe;
    }

    /**
     * Méthode permettant de récupérer l'arme du personnage
     *
     * @return l'arme
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Méthode permettant de définir une arme au personnage
     *
     * @param weapon la nouvelle arme
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Méthode permettant de récupérer le nom du personnage
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de définir le nom du personnage
     *
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant de récupérer le sexe du personnage
     *
     * @return le sexe
     */
    public Sexe getSexe() {
        return sexe;
    }

    /**
     * Méthode permettant de définir le sexe du personnage
     *
     * @param sexe le nouveau sexe
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
}
