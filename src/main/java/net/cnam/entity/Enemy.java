package net.cnam.entity;

import net.cnam.object.Weapon;

public class Enemy extends LivingEntity {

//CHAMPS
    private double strength;
    private double accuracy;
    private double speed;
    private int resistance;
    private int health;
    private Weapon weapon;
    
//CONSTRUCTEURS
    public Enemy(double strength, double accuracy, int resistance, int health, Weapon weapon, char caracter) {
        super(caracter);
        this.strength = strength;
        this.accuracy = accuracy;
        this.resistance = resistance;
        this.health = health;
        this.weapon = weapon;
    }
}
