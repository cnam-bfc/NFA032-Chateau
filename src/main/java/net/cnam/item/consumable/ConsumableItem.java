package net.cnam.item.consumable;

import net.cnam.entity.Player;

public interface ConsumableItem {

    //consume l'objet, donne le bénéfice à l'entité et détruit l'objet
    public void consume(Player player);
}
