package net.cnam.object.consumable;

import net.cnam.entity.Player;

public interface ConsumableObject {
    
    //consume l'objet, donne le bénéfice à l'entité et détruit l'objet
    public void consume(Player player);

}
