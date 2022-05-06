package net.cnam.entity;

import net.cnam.object.Location;
import net.cnam.object.Weapon;

public class Player extends Personage {

    public Player(Weapon weapon, String nom, Sexe sexe, int health, int resistance, Characteristic characteristics, Location location) {
        super(weapon, nom, sexe, health, resistance, characteristics, location);
    }

}
