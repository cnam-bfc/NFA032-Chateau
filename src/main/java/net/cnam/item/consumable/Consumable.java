package net.cnam.item.consumable;

import net.cnam.item.Item;
import net.cnam.item.PortableItem;

public abstract class Consumable extends Item implements ConsumableItem, PortableItem {

    public Consumable(String nom) {
        super(nom);
    }
}
