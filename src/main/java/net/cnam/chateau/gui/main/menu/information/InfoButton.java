package net.cnam.chateau.gui.main.menu.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class InfoButton extends CButton {
    private final App app;

    public InfoButton(App app) {
        super(app, "Informations");
        this.app = app;
    }

    @Override
    public void execute() {
        app.getConsole().show(new InfoMenu(app));
    }
}
