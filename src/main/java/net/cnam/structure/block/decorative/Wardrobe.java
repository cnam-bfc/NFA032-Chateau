package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

public class Wardrobe extends DecorativeBlock {

    public Wardrobe() {
        this(null);
    }

    public Wardrobe(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "W";
    }
}
