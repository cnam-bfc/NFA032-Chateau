package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.gui.component.CButton;

public class CageLeaveButton extends CButton {
    
    private CageMenu menu;
    
    public CageLeaveButton(CageMenu menu) {
        super("Partir de la cage");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }  
}
