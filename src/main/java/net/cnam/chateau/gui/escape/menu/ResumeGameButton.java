package net.cnam.chateau.gui.escape.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class ResumeGameButton extends CButton {
    private EscapeMenu escapeMenu;

    public ResumeGameButton(App app, EscapeMenu escapeMenu) {
        super(app, "Reprendre la partie");

        this.escapeMenu = escapeMenu;
    }

    @Override
    public void execute() {
        escapeMenu.stopDisplaying();
    }
}
