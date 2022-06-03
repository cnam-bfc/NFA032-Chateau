package net.cnam.chateau.gui.main.menu.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.main.menu.information.credits.CreditsMenu;

public class CreditButton extends CButton {

    private final App app;

    public CreditButton(App app) {
        super(app, "Crédits");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new CreditsMenu());
    }
}
