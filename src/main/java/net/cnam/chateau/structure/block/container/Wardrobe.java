package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.item.Item;

public class Wardrobe extends Container {

    public Wardrobe(Console console) {
        this(console, null);
    }

    public Wardrobe(Console console, Item hiddenItem) {
        super(console, "Armoire", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "W";
    }
}
