package net.cnam.entity.pet;

import net.cnam.entity.Characteristic;
import net.cnam.utils.Location;

public class Babe extends Pet {
    
    private boolean power = true;

    //TODO Voir pour faire suivre le joueur dans la location
    public Babe() {
        super(new Characteristic(50,50,10,10,10), "Babe", null);
    }
    
    @Override
    public void power(){
        if (!power) return;
        //TODO détruit un mur
        power = false;
    }
    
    public String scream(){
        return "groin-groin";
    }
}
