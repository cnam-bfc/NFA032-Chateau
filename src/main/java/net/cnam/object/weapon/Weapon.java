package net.cnam.object.weapon;

import net.cnam.object.Item;
import net.cnam.object.PortableObject;

public class Weapon extends Item implements PortableObject {

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
