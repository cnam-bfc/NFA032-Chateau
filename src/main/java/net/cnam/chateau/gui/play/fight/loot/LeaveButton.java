package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeaveButton extends CButton {
    private final LootMenu lootMenu;

    public LeaveButton(App app, LootMenu lootMenu) {
        super(app, "Quitter");

        this.lootMenu = lootMenu;
    }

    @Override
    public void execute() {
        lootMenu.stopLoopingMode();
    }
}
