package net.cnam.item.weapon;

import net.cnam.item.Item;
import net.cnam.item.PortableItem;

public class Weapon extends Item implements PortableItem {

    private int power;
    private int accuracy;
    private int speed;

    public Weapon(int power, int accuracy, int speed, String nom) {
        super(nom);
        this.power = power;
        this.accuracy = accuracy;
        this.speed = speed;
    }
}
