package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.gui.component.CButton;

public class LeaveBedButton extends CButton {

    private BedMenu menu;

    public LeaveBedButton(BedMenu menu) {
        super("Quitter");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}