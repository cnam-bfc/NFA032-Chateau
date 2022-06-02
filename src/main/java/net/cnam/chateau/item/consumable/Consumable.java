package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Entity;

public interface Consumable {
    // Méthode permettant de faire consommer un objet.
    void consume(Entity entity);
}
