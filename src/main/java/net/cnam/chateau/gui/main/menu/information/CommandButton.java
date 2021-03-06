package net.cnam.chateau.gui.main.menu.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.main.menu.information.commands.CommandsMenu;

public class CommandButton extends CButton {
    private final App app;

    public CommandButton(App app) {
        super(app, "Afficher les commandes");

        this.app = app;
    }

    @Override
    public void execute() {
        app.getConsole().show(new CommandsMenu());
    }
}