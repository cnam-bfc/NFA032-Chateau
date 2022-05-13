package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.utils.Location;

public class PepeLoiseau extends Pet {
    
    private boolean power = true;

    //TODO Voir pour faire suivre le joueur dans la location
    public PepeLoiseau() {
        super(new Characteristic(50,50,10,10,10), "Pepe Loiseau", null);
    }
    
    @Override
    public void power(){
        if (!power) return;
        //TODO Dévoile les pièces autours de celles du joueur
        power = false;
    }
    
    public String scream(){
        return "couic-couic";
    }
}
