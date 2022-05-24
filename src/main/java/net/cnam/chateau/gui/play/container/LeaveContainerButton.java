package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class LeaveContainerButton extends CButton {
    
    private ContainerMenu menu;
    
    public LeaveContainerButton(ContainerMenu menu, Container block) {
        super("Quitter");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }  
}
