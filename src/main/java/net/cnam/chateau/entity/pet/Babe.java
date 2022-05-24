package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Player;

public class Babe extends Pet {

    private boolean power = true;

    public Babe(Player player) {
        super(player, "Babe");
    }
    
    public Babe() {
        super("Babe");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO détruit un mur
        power = false;
    }

    @Override
    public String scream() {
        return "groin-groin";
    }
}
