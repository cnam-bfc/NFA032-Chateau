package net.cnam.chateau.gui.escape.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class QuitGameButton extends CButton {
    private final App app;
    private final EscapeMenu escapeMenu;

    public QuitGameButton(App app, EscapeMenu escapeMenu) {
        super(app, "Quitter la partie");

        this.app = app;
        this.escapeMenu = escapeMenu;
    }

    @Override
    public void execute() {
        escapeMenu.stopDisplaying();
        app.getCurrentGame().stop();
    }
}
