package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Player;

public class Idefix extends Pet {

    private boolean power = true;

    public Idefix(Player player) {
        super(player, "Idefix");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Augmente temporairement la force (potion dans asterix et obelix)
        power = false;
    }

    @Override
    public String scream() {
        return "Ouaf";
    }
}
