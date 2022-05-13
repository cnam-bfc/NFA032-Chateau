package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.utils.Location;

public class PanPan extends Pet {
    
    private boolean power = true;

    //TODO Voir pour faire suivre le joueur dans la location
    public PanPan() {
        super(new Characteristic(50,50,10,10,10), "PanPan", null);
    }
    
    @Override
    public void power(){
        if (!power) return;
        //TODO détere un objet aléatoire rare
        power = false;
    }
    
    public String scream(){
        return "Knknknkn";
    }
}
