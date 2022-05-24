package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.item.Item;

public class Wardrobe extends BlockContainer {
    
    public Wardrobe(Console console) {
        super(console, "Wardrobe", null);
    }

    public Wardrobe(Console console, Item hiddenItem) {
        super(console, "Wardrobe", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "W";
    }
}
