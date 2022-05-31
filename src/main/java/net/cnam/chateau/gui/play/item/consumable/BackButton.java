package net.cnam.chateau.gui.play.item.consumable;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {
    private final ConsumableItemMenu consumableItemMenu;

    public BackButton(App app, ConsumableItemMenu consumableItemMenu) {
        super(app, "Retour");

        this.consumableItemMenu = consumableItemMenu;
    }

    @Override
    public void execute() {
        consumableItemMenu.stopDisplaying();
    }
}
