package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;

public class UseItemButton extends CButton {
    private final Item item;

    public UseItemButton(App app, Item item) {
        super(app, "Utiliser\n" + item.getName());

        this.item = item;
    }

    @Override
    public void execute() {
        // TODO Use item in fight
    }
}
