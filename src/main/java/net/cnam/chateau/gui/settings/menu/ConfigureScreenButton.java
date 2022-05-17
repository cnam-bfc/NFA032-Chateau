package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;

public class ConfigureScreenButton extends CButton {

    private final Console console;

    public ConfigureScreenButton(Console console) {
        super("Configurer les dimensions de la fenêtre");

        this.console = console;
    }

    @Override
    public void execute() {
        console.adjustSize();
    }
}
