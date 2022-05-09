package net.cnam.object.consumable;

import net.cnam.entity.Player;

public class PotionSecrete extends Consumable {

    public PotionSecrete() {
        super("Potion secrètre");
    }
    
    @Override
    public void consume(Player player) {
        //Effet aléatoire parmis les autres potions
    }

}
