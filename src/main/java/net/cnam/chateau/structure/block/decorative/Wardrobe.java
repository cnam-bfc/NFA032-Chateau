package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

public class Wardrobe extends DecorativeBlock {

    public Wardrobe() {
        this(null);
    }

    public Wardrobe(Item hiddenItem) {
        super("Wardrobe", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "W";
    }
}
