package net.cnam.chateau.item.weapon;

import net.cnam.chateau.item.Item;

public class Weapon extends  Item{

    private int power;
    private int accuracy;
    private int speed;

    public Weapon(String name, String description, int power, int accuracy, int speed) {
        super(name, description);

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
