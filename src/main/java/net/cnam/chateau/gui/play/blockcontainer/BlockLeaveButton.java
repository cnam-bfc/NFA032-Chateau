package net.cnam.chateau.gui.play.blockcontainer;

import net.cnam.chateau.gui.component.CButton;

public class BlockLeaveButton extends CButton {
    
    private BlockMenu menu;
    
    public BlockLeaveButton(BlockMenu menu) {
        super("Partir");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }  
}
