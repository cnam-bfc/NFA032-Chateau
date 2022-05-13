package net.cnam.chateau.gui.mainmenu;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;

public class SettingsButton extends CButton {

    private final Console console;

    public SettingsButton(Console console) {
        super("2. Paramètres");

        this.console = console;
    }

    @Override
    public void execute() {
        console.adjustSize();
    }
}
