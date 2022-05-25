package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class LeaveButton extends CButton {
    private final DoorMenu menu;

    public LeaveButton(AppSettings settings, DoorMenu menu) {
        super(settings, "Partir");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
