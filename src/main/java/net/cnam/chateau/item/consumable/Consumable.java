package net.cnam.chateau.item.consumable;

import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.PortableItem;

public abstract class Consumable extends Item implements ConsumableItem, PortableItem {

    public Consumable(String nom) {
        super(nom);
    }
}
