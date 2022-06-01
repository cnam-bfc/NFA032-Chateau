package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.item.Item;

import java.util.Random;

public class AttackPotion extends Item implements Consumable {
    private static final int MIN_ATTACK = 15;
    private static final int MAX_ATTACK = 40;

    private final int damage;

    /**
     * Constructeur
     *
     * @param random objet random permettant de définir l'aléatoire de la potion
     */
    public AttackPotion(Random random) {
        super("", "Potion infligeant des dégâts !");

        this.damage = random.nextInt(MIN_ATTACK, MAX_ATTACK);
        this.setName(generateName());
    }

    public int getDamage() {
        return damage;
    }

    // Méthode permettant de générer un nom à la potion en fonction son champ heal
    private String generateName() {
        int mediumPotion = (MIN_ATTACK + MAX_ATTACK - 3) / 2;
        int greatPotion = mediumPotion + ((MIN_ATTACK + MAX_ATTACK - 3) / 4);
        if (this.damage < mediumPotion) {
            return "Petite potion d'attaque";
        } else if (this.damage < greatPotion) {
            return "Potion d'attaque moyenne";
        } else {
            return "Grande potion d'attaque";
        }
    }

    /**
     * Redéfinition de la méthode consume permettant de consommer l'objet
     *
     * @param entity l'entité qui sera soignée par la potion.
     */
    @Override
    public void consume(Entity entity) {
        try {
            entity.damage(damage);
        } catch (EntityDeadException ignored) {
        }
    }
}
