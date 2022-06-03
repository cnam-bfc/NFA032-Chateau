package net.cnam.chateau.gui.play.finish;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class QuitButton extends CButton {
    private final FinishMenu finishMenu;

    public QuitButton(App app, FinishMenu finishMenu) {
        super(app, "Quitter la partie");

        this.finishMenu = finishMenu;
    }

    @Override
    public void execute() {
        finishMenu.stopLoopingMode();
    }
}
