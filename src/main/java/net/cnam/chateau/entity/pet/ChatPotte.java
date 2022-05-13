package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.utils.Location;

public class ChatPotte extends Pet {
    
    private boolean power = true;

    //TODO Voir pour faire suivre le joueur dans la location
    public ChatPotte() {
        super(new Characteristic(50,50,10,10,10), "ChatPotte", null);
    }
    
    @Override
    public void power(){
        if (!power) return;
        //TODO Vole l'arme de l'adversaire
        power = false;
    }
    
    public String scream(){
        return "Miaou";
    }
}
