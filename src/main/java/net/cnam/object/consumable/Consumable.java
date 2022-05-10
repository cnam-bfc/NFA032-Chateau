package net.cnam.object.consumable;

import net.cnam.object.Item;
import net.cnam.object.PortableObject;

public abstract class Consumable extends Item implements ConsumableObject, PortableObject{

    public Consumable(String nom) {
        super(nom);
    }

}
