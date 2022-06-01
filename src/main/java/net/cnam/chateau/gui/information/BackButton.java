package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {
    private final InfoMenu menu;

    public BackButton(App app, InfoMenu menu) {
        super(app, "Retour");

        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stopDisplaying();
    }
}

