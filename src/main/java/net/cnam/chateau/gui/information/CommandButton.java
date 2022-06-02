package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.information.commands.CommandsMenu;

public class CommandButton extends CButton {

    private App app;

    public CommandButton(App app) {
        super(app, "Afficher les commandes");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new CommandsMenu(app));
    }
}