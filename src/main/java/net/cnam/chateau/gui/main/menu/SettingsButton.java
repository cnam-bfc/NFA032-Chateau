package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.menu.SettingsMenu;

public class SettingsButton extends CButton {

    private final Console console;

    public SettingsButton(Console console) {
        super("2. Paramètres");

        this.console = console;
    }

    @Override
    public void execute() {
        console.show(new SettingsMenu(console));
    }
}
