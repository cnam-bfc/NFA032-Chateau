package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

public class Desk extends DecorativeBlock {

    public Desk() {
        this(null);
    }

    public Desk(Item hiddenItem) {
        super("Desk", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "D";
    }
}
