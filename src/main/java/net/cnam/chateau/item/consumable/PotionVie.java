package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.item.Item;

import java.util.Random;

public class PotionVie extends Item implements Consumable {

    private final static int MIN_HP_HEALTH = 5;
    private final static int MAX_HP_HEALTH = 21;

    private int hp;
    public PotionVie(Random random) {
        super("Potion de régénération", "Permet de récupérer de la vie");

        this.hp = random.nextInt(MIN_HP_HEALTH,MAX_HP_HEALTH);
    }

    @Override
    public void consume(Player player) {
        player.heal(this.hp);
        player.setItem(null);
    }
}
