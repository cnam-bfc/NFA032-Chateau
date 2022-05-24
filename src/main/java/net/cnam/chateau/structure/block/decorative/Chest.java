package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends DecorativeBlock {

    public Chest() {
        this(null);
    }

    public Chest(Item hiddenItem) {
        super("Chest", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
