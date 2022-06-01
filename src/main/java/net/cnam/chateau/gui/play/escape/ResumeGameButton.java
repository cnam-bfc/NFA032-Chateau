package net.cnam.chateau.gui.play.escape;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class ResumeGameButton extends CButton {
    private final EscapeMenu escapeMenu;

    public ResumeGameButton(App app, EscapeMenu escapeMenu) {
        super(app, "Reprendre la partie");

        this.escapeMenu = escapeMenu;
    }

    @Override
    public void execute() {
        escapeMenu.stopDisplaying();
    }
}
