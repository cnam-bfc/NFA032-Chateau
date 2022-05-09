package net.cnam.object.consumable;

import net.cnam.object.Item;

public class Consumable extends Item implements ConsumableObject{

    public Consumable(String nom) {
        super(nom);
    }

    @Override
    public void consume() {
    }

}
