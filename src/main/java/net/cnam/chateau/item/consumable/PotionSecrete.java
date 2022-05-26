package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.item.Item;

public class PotionSecrete extends Item implements Consumable {

    public PotionSecrete() {
        super("Potion secrètre", "Son effet va vous surprendre !");
    }

    @Override
    public void consume(Player player) {
        //Effet aléatoire parmis les autres potions
    }
}
