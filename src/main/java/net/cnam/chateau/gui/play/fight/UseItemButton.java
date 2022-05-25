package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;

public class UseItemButton extends CButton {
    private final Item item;

    public UseItemButton(AppSettings settings, Item item) {
        super(settings, "Utiliser " + item.getName());

        this.item = item;
    }

    @Override
    public void execute() {
        // TODO Use item in fight
    }
}
