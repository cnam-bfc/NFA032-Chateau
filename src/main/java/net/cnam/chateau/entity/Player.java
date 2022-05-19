package net.cnam.chateau.entity;

import net.cnam.chateau.entity.pet.*;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.item.weapon.Weapon;

public class Player extends Personage {

    private Pet pet = new PepeLoiseau(new Location(this.getLocation().getX(), this.getLocation().getY()));

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
        return CColor.CYAN + super.getCharacter() + CColor.CYAN.getForegroundReset();
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public boolean havePet() {
        return (this.pet != null);
    }
}
