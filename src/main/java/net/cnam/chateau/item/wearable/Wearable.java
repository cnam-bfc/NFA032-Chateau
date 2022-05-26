package net.cnam.chateau.item.wearable;

import net.cnam.chateau.item.Item;

public class Wearable extends Item {

    private int strength;
    private int accuracy;
    private int speed;

    public Wearable(String name, String description, int strength, int accuracy, int speed) {
        super(name, description);

        this.strength = strength;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
