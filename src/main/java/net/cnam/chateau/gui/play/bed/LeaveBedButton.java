package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeaveBedButton extends CButton {
    private final BedMenu menu;

    public LeaveBedButton(App app, BedMenu menu) {
        super(app, "Quitter");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}