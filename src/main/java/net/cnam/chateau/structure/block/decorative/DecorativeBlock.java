package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.Block;

public abstract class DecorativeBlock extends Block {

    private Item hiddenItem;
    private String name;

    public DecorativeBlock(String name ,Item hiddenItem) {
        this.name = name;
        this.hiddenItem = hiddenItem;
    }

    public boolean hasItem() {
        return this.hiddenItem != null;
    }

    public Item getHiddenItem() {
        return hiddenItem;
    }

    public void setHiddenItem(Item hiddenItem) {
        this.hiddenItem = hiddenItem;
    }

    public String getName(){
        return this.name;
    }
}
