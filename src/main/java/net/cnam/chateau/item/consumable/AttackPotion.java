package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.item.Item;

import java.util.Random;

public class AttackPotion extends Item implements Consumable{

    private static final int MIN_ATTACK = 5;
    private static final int MAX_ATTACK = 15;

    private final int damage;

    public AttackPotion(Random random) {
        super("","Potion infligeant des dégats !");

        this.damage = random.nextInt(MIN_ATTACK,MAX_ATTACK);
        this.setName(generateName());
    }

    private String generateName() {
        int mediumPotion = (MIN_ATTACK + MAX_ATTACK - 3) / 2;
        int greatPotion = mediumPotion + ((MIN_ATTACK + MAX_ATTACK- 3) / 4);
        if (this.damage < mediumPotion) {
            return "Petite potion d'attaque";
        } else if (this.damage < greatPotion) {
            return "Potion d'attaque moyenne";
        } else {
            return "Grande potion d'attaque";
        }
    }

    @Override
    public void consume(Entity entity) {
        try {
            entity.damage(damage);
        } catch (EntityDeadException ignored) {
        }
    }
}
