package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeaveCageButton extends CButton {
    private final CageMenu menu;

    public LeaveCageButton(App app, CageMenu menu) {
        super(app, "Quitter");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopLoopingMode();
    }
}
