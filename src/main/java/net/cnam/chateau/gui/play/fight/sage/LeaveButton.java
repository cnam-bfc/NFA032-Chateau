package net.cnam.chateau.gui.play.fight.sage;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeaveButton extends CButton {
    private final SageDoorMenu menu;

    public LeaveButton(App app, SageDoorMenu menu) {
        super(app, "Partir");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopLoopingMode();
    }
}
