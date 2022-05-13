package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

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
