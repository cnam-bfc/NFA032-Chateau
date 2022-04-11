package net.cnam.object;

/**
 *
 * @author Rouault Alban <alban.rouault.auditeur@lecnam.net>
 */
public class Weapon extends Object {

//CHAMPS
    private int durability;
    private int power;
    private int malusAccuracy;

//CONSTRUCTEUR
    public Weapon(int durability, int power, int malusAccuracy) {
        this.durability = durability;
        this.power = power;
        this.malusAccuracy = malusAccuracy;
    }
    
    
    
}
