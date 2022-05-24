package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.gui.component.CButton;

public class BedLeaveButton extends CButton {
    
    private BedMenu menu;
    
    public BedLeaveButton (BedMenu menu) {
        super("Ne pas dormir");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }  
    }