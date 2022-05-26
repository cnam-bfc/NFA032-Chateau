package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;

public class PotionVie extends Consumable {

    public PotionVie() {
        super("Potion de régénération", "Permet de récupérer de la vie");
    }

    @Override
    public void consume(Player player) {
        //TODO remet de la vie au joueur (pas de vie en +, vie supplémentaire = delete)
    }
}
