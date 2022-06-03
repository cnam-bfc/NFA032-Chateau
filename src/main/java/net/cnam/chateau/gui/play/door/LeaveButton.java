package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeaveButton extends CButton {
    private final DoorMenu menu;

    public LeaveButton(App app, DoorMenu menu) {
        super(app, "Partir");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopLoopingMode();
    }
}
