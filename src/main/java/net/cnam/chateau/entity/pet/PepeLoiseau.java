package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Player;

public class PepeLoiseau extends Pet {

    private boolean power = true;

    public PepeLoiseau(Player player) {
        super(player, "Pepe Loiseau");
    }
    
    public PepeLoiseau() {
        super("Pepe Loiseau");
    }


    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Dévoile les pièces autours de celles du joueur
        power = false;
    }

    @Override
    public String scream() {
        return "couic-couic";
    }
}
