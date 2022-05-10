package net.cnam.item.consumable;

import net.cnam.entity.Player;

public class PotionVie extends Consumable {

    public PotionVie() {
        super("Potion de régénération");
    }

    @Override
    public void consume(Player player) {
        //TODO remet de la vie au joueur (pas de vie en +, vie supplémentaire = delete)
    }
}
