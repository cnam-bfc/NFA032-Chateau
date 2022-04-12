package net.cnam.entity;

import net.cnam.object.Weapon;

public class Personage extends LivingEntity{

    
//CHAMPS
    private Statistic stats;
    private int resistance;
    private int health;
    private Weapon weapon;
    private String nom;
    private Sexe sexe;
    
//CONSTRUCTEURS
    public Personage(Statistic stats, int resistance, int health, Weapon weapon, String nom, Sexe sexe, char caracter) {
        super(caracter);
        this.stats = stats;
        this.resistance = resistance;
        this.health = health;
        this.weapon = weapon;
        this.nom = nom;
        this.sexe = sexe;
    }

}
    
