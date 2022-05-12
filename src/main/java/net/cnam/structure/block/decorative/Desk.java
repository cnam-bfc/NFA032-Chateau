package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

public class Desk extends DecorativeBlock {

    public Desk() {
        this(null);
    }

    public Desk(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "D";
    }
}
