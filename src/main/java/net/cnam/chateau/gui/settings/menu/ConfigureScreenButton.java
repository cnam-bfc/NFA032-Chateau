package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class ConfigureScreenButton extends CButton {
    private final App app;

    public ConfigureScreenButton(App app) {
        super(app.getSettings(), "Configurer les dimensions de la fenêtre");

        this.app = app;
    }

    @Override
    public void execute() {
        app.getConsole().adjustSize();
    }
}
