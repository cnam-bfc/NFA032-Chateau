package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.gui.component.CButton;

public class LeaveCageButton extends CButton {

    private CageMenu menu;

    public LeaveCageButton(CageMenu menu) {
        super("Quitter");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
