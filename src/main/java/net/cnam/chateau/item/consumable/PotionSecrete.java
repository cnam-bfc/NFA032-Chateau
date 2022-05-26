package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;

public class PotionSecrete extends Consumable {

    public PotionSecrete() {
        super("Potion secrètre", "Son effet va vous surprendre !");
    }

    @Override
    public void consume(Player player) {
        //Effet aléatoire parmis les autres potions
    }
}
