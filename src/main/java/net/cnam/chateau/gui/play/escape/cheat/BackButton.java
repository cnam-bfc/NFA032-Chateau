package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {
    private final CheatMenu menu;

    public BackButton(App app, CheatMenu menu) {
        super(app, "Retour");

        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stopLoopingMode();
    }
}
