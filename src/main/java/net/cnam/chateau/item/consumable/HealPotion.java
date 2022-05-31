package net.cnam.chateau.item.consumable;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.item.Item;

import java.util.Random;

public class HealPotion extends Item implements Consumable {
    private static final int MIN_HEAL = 10;
    private static final int MAX_HEAL = 30;

    private final int heal;

    /**
     * Constructeur
     *
     * @param random objet random permettant de définir l'aléatoire de la potion
     */
    public HealPotion(Random random) {
        super("", "Potion permettant de regagner des points de vie !");

        this.heal = random.nextInt(MIN_HEAL, MAX_HEAL);
        this.setName(generateName());
    }

    // Méthode permettant de générer un nom à la potion en fonction son champ heal
    private String generateName() {
        int mediumPotion = (MIN_HEAL + MAX_HEAL - 3) / 2;
        int greatPotion = mediumPotion + ((MIN_HEAL + MAX_HEAL - 3) / 4);
        if (this.heal < mediumPotion) {
            return "Petite potion de soin";
        } else if (this.heal < greatPotion) {
            return "Potion de soin moyenne";
        } else {
            return "Grande potion de soin";
        }
    }

    /**
     * Redéfinition de la méthode consume permettant de consommer l'objet
     *
     * @param entity l'entité qui sera soignée par la potion.
     */
    @Override
    public void consume(Entity entity) {
        entity.heal(heal);
    }
}