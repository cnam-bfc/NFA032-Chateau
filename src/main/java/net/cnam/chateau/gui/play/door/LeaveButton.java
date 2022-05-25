package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.gui.component.CButton;

public class LeaveButton extends CButton {
    
    private final DoorMenu menu;
    
    public LeaveButton(DoorMenu menu) {
        super("Partir");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
