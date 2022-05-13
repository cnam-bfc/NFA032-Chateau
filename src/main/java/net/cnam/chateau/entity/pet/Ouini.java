package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.utils.Location;

public class Ouini extends Pet {
    
    private boolean power = true;

    //TODO Voir pour faire suivre le joueur dans la location
    public Ouini() {
        super(new Characteristic(50,50,10,10,10), "Ouini", null);
    }
    
    @Override
    public void power(){
        if (!power) return;
        //TODO Restaure de la santé (miel)
        power = false;
    }
    
    public String scream(){
        return "Rrrrr";
    }
}
