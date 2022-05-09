package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;

public class SettingsButton extends CButton {

    private final App app;

    public SettingsButton(App app) {
        super("2. Paramètres");

        this.app = app;
    }

    @Override
    public void execute() {
        Console console = app.getConsole();
        console.adjustSize(app);
        app.getMainMenu().setSize(console.getLength(), console.getHeight());
    }
}
