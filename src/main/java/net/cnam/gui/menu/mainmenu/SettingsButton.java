package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.gui.component.CButton;

public class SettingsButton extends CButton {

    private final App app;

    public SettingsButton(App app) {
        super("2. Paramètres");

        this.app = app;
    }

    @Override
    public void execute() {
        MainMenu mainMenu = app.getMainMenu();
        app.getConsole().getContent().remove(mainMenu);
        app.getConsole().adjustSize();
        mainMenu.setSize(app.getConsole().getLength(), app.getConsole().getHeight());
        app.getConsole().getContent().add(mainMenu);
    }
}
