package net.cnam.object;

/**
 *
 * @author Rouault Alban <alban.rouault.auditeur@lecnam.net>
 */
public class Weapon extends Object {

//CHAMPS
    private int durability;
    private int power;

//CONSTRUCTEUR
    public Weapon(int durability, int power) {
        this.durability = durability;
        this.power = power;
    }
    
    
    
}
