package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.menu.PlayMenu;

public class PlayButton extends CButton {
    private final App app;
    private final MainMenu mainMenu;

    public PlayButton(App app, MainMenu mainMenu) {
        super("Jouer");

        this.app = app;
        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new PlayMenu(app, mainMenu));
    }
}
