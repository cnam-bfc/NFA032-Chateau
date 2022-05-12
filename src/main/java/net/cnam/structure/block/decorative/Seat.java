package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

public class Seat extends DecorativeBlock {

    public Seat() {
        this(null);
    }

    public Seat(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "S";
    }
}
