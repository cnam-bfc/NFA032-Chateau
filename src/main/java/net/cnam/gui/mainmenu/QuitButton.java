package net.cnam.gui.mainmenu;

import net.cnam.gui.component.CButton;

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
