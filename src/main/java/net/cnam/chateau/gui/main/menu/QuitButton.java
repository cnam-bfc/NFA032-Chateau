package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class QuitButton extends CButton {
    private final MainMenu mainMenu;

    public QuitButton(App app, MainMenu mainMenu) {
        super(app, "Quitter");

        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        mainMenu.stopLoopingMode();
    }
}
