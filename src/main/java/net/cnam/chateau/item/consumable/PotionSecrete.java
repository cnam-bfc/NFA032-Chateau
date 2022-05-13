package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;

public class PotionSecrete extends Consumable {

    public PotionSecrete() {
        super("Potion secrètre");
    }

    @Override
    public void consume(Player player) {
        //Effet aléatoire parmis les autres potions
    }
}
