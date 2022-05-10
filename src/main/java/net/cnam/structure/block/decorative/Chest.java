package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends DecorativeBlock {

    public Chest() {
        this(null);
    }

    public Chest(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
