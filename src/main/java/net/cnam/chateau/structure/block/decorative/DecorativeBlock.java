package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.Block;

public abstract class DecorativeBlock extends Block {

    private Item hiddenItem;

    public DecorativeBlock(Item hiddenItem) {
        this.hiddenItem = hiddenItem;
    }

    public boolean hasItem() {
        return this.hiddenItem != null;
    }

    public Item getHiddenItem() {
        return hiddenItem;
    }
}
