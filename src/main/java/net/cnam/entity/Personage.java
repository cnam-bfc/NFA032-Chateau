package net.cnam.entity;

import net.cnam.object.Weapon;

/**
 *
 * @author Rouault Alban <alban.rouault.auditeur@lecnam.net>
 */
public class Personage extends LivingEntity{

    
//CHAMPS
    private double strength;
    private double accuracy;
    private int resistance;
    private int health;
    private Weapon weapon;
    
//CONSTRUCTEURS
    public Personage(double strength, double accuracy, int resistance, int health, Weapon weapon) {
        this.strength = strength;
        this.accuracy = accuracy;
        this.resistance = resistance;
        this.health = health;
        this.weapon = weapon;
    }
}