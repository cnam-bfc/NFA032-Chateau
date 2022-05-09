package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.gui.component.CButton;

public class QuitButton extends CButton {

    private final App app;

    public QuitButton(App app) {
        super("3. Quitter");

        this.app = app;
    }

    @Override
    public void execute() {
        app.stop();
    }
}
