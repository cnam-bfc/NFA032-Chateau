package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Player;

public class PanPan extends Pet {

    private boolean power = true;

    public PanPan(Player player) {
        super(player, "PanPan");
    }
    
    public PanPan() {
        super("PanPan");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO détere un objet aléatoire rare
        power = false;
    }

    @Override
    public String scream() {
        return "Knknknkn";
    }
}
