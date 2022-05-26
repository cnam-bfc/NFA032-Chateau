package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.item.Item;

public abstract class Consumable extends Item {

    public Consumable(String nom, String description) {
        super(nom, description);
    }

    abstract void consume(Player player);
}
