package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.gui.component.CButton;

public class PlayButton extends CButton {

    private final App app;

    public PlayButton(App app) {
        super("1. Jouer");

        this.app = app;
    }

    @Override
    public void execute() {
        MainMenu mainMenu = app.getMainMenu();
        app.getConsole().getContent().remove(mainMenu);
        app.startGame();
        app.getConsole().getContent().add(mainMenu);
    }
}
