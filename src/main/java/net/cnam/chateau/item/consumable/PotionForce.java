package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;

public class PotionForce extends Consumable {

    public PotionForce() {
        super("Potion de force");
    }

    @Override
    public void consume(Player player) {
        //TODO augmente la force du joueur de 5 pendant 3 rounds
    }
}
