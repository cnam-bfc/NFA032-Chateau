package net.cnam.chateau.gui.play.sagedoor;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class LeaveButton extends CButton {
    private final SageDoorMenu menu;

    public LeaveButton(AppSettings settings, SageDoorMenu menu) {
        super(settings, "Partir");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
