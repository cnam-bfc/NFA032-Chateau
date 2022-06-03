package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeaveContainerButton extends CButton {
    private final ContainerMenu menu;

    public LeaveContainerButton(App app, ContainerMenu menu) {
        super(app, "Quitter");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopLoopingMode();
    }
}
