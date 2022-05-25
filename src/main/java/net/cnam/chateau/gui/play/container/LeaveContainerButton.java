package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class LeaveContainerButton extends CButton {
    private final ContainerMenu menu;

    public LeaveContainerButton(AppSettings settings, ContainerMenu menu) {
        super(settings, "Quitter");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
