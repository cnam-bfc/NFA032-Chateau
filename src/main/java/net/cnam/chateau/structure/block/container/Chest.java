package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends Container {

    public Chest(Console console) {
        this(console, null);
    }

    public Chest(Console console, Item hiddenItem) {
        super(console, "Coffre", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return super.getCharacter("C");
    }
}
