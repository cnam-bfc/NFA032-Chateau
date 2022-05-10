package net.cnam.gui.mainmenu;

import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;

public class SettingsButton extends CButton {

    private final Console console;
    private final MainMenu mainMenu;

    public SettingsButton(Console console, MainMenu mainMenu) {
        super("2. Paramètres");

        this.console = console;
        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        console.adjustSize();
        mainMenu.setSize(console.getLength(), console.getHeight());
    }
}
