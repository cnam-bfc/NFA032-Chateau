package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

public class Table extends DecorativeBlock {

    public Table() {
        this(null);
    }

    public Table(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "T";
    }
}
