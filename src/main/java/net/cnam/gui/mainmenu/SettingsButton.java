package net.cnam.gui.mainmenu;

import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;

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
