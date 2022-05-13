package net.cnam.chateau.item.weapon;

import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.PortableItem;

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

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
