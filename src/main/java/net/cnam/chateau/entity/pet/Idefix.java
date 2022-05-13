package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.utils.Location;

public class Idefix extends Pet {
    
    private boolean power = true;

    //TODO Voir pour faire suivre le joueur dans la location
    public Idefix() {
        super(new Characteristic(50,50,10,10,10), "Idefix", null);
    }
    
    @Override
    public void power(){
        if (!power) return;
        //TODO Augmente temporairement la force (potion dans asterix et obelix) 
        power = false;
    }
    
    public String scream(){
        return "Ouaf";
    }
}
