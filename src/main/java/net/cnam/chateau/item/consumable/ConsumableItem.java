package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;

public interface ConsumableItem {

    //consume l'objet, donne le bénéfice à l'entité et détruit l'objet
    void consume(Player player);
}
