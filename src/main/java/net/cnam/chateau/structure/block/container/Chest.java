package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends BlockContainer {
    
    public Chest(Console console) {
        super(console, "Chest", null);
    }

    public Chest(Console console, Item hiddenItem) {
        super(console, "Chest", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
