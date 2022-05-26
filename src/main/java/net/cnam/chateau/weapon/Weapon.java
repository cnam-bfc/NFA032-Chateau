package net.cnam.chateau.weapon;

import net.cnam.chateau.item.Item;

public class Weapon{

    String name;
    String description;
    private int power;
    private int accuracy;
    private int speed;

    public Weapon(String name, String description, int power, int accuracy, int speed) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
