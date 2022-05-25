package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class LeaveCageButton extends CButton {
    private final CageMenu menu;

    public LeaveCageButton(AppSettings settings, CageMenu menu) {
        super(settings, "Quitter");
        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
