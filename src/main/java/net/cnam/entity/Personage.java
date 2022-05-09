package net.cnam.entity;

import net.cnam.object.Location;
import net.cnam.object.weapon.Weapon;

/**
 * Classe d'un personnage.
 *
 */
public class Personage extends LivingEntity {
    
    private Sexe sexe;

    /**
     * Constructeur du personnage sans arme
     * 
     * @param sexe sexe du personnage
     * @param characteristics fiche de caractéristique du personnage
     * @param location position du personnage
     * @param nom nom du personnage
     */
    public Personage(Sexe sexe, Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
        this.sexe = sexe;
    }

    /**
     * Constructeur du personnage avec une arme
     * 
     * @param sexe sexe du personnage
     * @param characteristics fiche de caractéristique du personnage
     * @param weapon arme du personnage
     * @param location position du personnage
     * @param nom nom du personnage
     */
    public Personage(Sexe sexe, Characteristic characteristics, Weapon weapon, String nom, Location location) {
        super(characteristics, weapon, nom, location);
        this.sexe = sexe;
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
