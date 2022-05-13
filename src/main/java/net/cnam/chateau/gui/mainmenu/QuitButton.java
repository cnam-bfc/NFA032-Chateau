package net.cnam.chateau.gui.mainmenu;

import net.cnam.chateau.gui.component.CButton;

public class QuitButton extends CButton {

    private final MainMenu mainMenu;

    public QuitButton(MainMenu mainMenu) {
        super("3. Quitter");

        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        mainMenu.stopDisplaying();
    }
}