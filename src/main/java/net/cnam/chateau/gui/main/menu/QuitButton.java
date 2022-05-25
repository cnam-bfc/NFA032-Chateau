package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class QuitButton extends CButton {
    private final MainMenu mainMenu;

    public QuitButton(AppSettings settings, MainMenu mainMenu) {
        super(settings, "Quitter");

        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        mainMenu.stopDisplaying();
    }
}
