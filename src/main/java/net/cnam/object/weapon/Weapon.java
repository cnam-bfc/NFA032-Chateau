package net.cnam.object.weapon;

import net.cnam.object.Item;

public class Weapon extends Item {

    private int power;
    private int malusAccuracy;

    public Weapon(String nom, int power, int malusAccuracy) {
        super(nom);
        this.power = power;
        this.malusAccuracy = malusAccuracy;
    }
}
