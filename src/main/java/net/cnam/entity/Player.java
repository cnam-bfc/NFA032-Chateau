package net.cnam.entity;

import net.cnam.utils.Location;
import net.cnam.item.weapon.Weapon;

public class Player extends Personage {

    /**
     * Constructeur du personnage sans arme
     * 
     * @param sexe sexe du personnage
     * @param characteristics fiche de caractéristique du personnage
     * @param location position du personnage
     * @param nom nom du personnage
     */
    public Player(Sexe sexe, Characteristic characteristics, Location location, String nom) {
        super(sexe, characteristics, nom, location);
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
    public Player(Sexe sexe, Characteristic characteristics, Weapon weapon, String nom, Location location) {
        super(sexe, characteristics, weapon, nom, location);
    }

    @Override
    public String getCharacter() {
        return "\u001b[32m" + super.getCharacter() + "\u001b[39m";
    }
}
