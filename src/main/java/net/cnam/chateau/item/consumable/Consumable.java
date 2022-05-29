package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Entity;

interface Consumable{

    // Méthode permettant de faire consommer un objet.
    abstract void consume(Entity entity);
}
