package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class LeaveBedButton extends CButton {
    private final BedMenu menu;

    public LeaveBedButton(AppSettings settings, BedMenu menu) {
        super(settings, "Quitter");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}