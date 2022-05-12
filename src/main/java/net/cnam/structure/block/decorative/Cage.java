package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la map.
 */
public class Cage extends DecorativeBlock {

    public Cage() {
        this(null);
    }

    public Cage(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "P";
    }
}
