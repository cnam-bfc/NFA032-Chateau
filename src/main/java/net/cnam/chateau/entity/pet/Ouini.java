package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Player;

public class Ouini extends Pet {

    private boolean power = true;

    public Ouini(Player player) {
        super(player, "Ouini");
    }
    
    public Ouini() {
        super("Ouini");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Restaure de la santé (miel)
        power = false;
    }

    @Override
    public String scream() {
        return "Rrrrr";
    }
}
