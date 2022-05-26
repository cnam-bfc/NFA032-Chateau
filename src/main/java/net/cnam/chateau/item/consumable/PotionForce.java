package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.item.Item;

public class PotionForce extends Item implements Consumable {

    public PotionForce() {
        super("Potion de force", "Augmente temporairement la force !");
    }

    @Override
    public void consume(Player player) {
        //TODO augmente la force du joueur de 5 pendant 3 rounds
    }
}
