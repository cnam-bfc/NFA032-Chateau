package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la map.
 */
public class Cage extends DecorativeBlock {

    public Cage() {
        this(null);
    }

    public Cage(Item hiddenItem) {
        super("Cage", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "P";
    }
}
