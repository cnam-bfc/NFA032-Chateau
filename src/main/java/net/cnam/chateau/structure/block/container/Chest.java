package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.App;
import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends Container {

    public Chest(App app) {
        this(app, null);
    }

    public Chest(App app, Item hiddenItem) {
        super(app, "Coffre", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return super.getCharacter("C");
    }
}
