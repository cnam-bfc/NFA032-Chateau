package net.cnam.entity;

import net.cnam.object.Location;
import net.cnam.object.Weapon;

/**
 * Classe d'un personnage.
 *
 */
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
     * @param location Coordonnées de l'entité
     */
    public Personage(Weapon weapon, String nom, Sexe sexe, int health, int resistance, Characteristic characteristics, Location location) {
        super(health, resistance, characteristics, location);
        this.weapon = weapon;
        this.nom = nom;
        this.sexe = sexe;
    }

    /**
     * Méthode permettant de récupérer l'arme du personnage.
     *
     * @return un objet weapon (arme)
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Méthode permettant de définir l'arme du personnage.
     *
     * @param weapon Objet Weapon (arme)
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Méthode permettant de récupérer le nom du personnage.
     *
     * @return un string "nom"
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de définir le nom du personnage.
     *
     * @param nom String "nom personnage"
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant de récupérer le sexe du personnage.
     *
     * @return un String "sexe"
     */
    public Sexe getSexe() {
        return sexe;
    }

    /**
     * Méthode permettant de définir le sexe du personnage. Les sexes sont dans
     * l'énumération net.cnam.entity.Sexe.java
     *
     * @param sexe String "sexe du personnage"
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    @Override
    public String getCharacter() {
        return "P";
    }
}
