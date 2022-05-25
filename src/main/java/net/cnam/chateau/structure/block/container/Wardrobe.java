package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.App;
import net.cnam.chateau.item.Item;

public class Wardrobe extends Container {

    public Wardrobe(App app) {
        this(app, null);
    }

    public Wardrobe(App app, Item hiddenItem) {
        super(app, "Armoire", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return super.getCharacter("W");
    }
}
