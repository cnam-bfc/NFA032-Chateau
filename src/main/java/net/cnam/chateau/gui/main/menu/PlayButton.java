package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.menu.PlayMenu;

public class PlayButton extends CButton {

    private final Console console;
    private final AppSettings settings;
    private final MainMenu mainMenu;

    public PlayButton(Console console, AppSettings settings, MainMenu mainMenu) {
        super("Jouer");

        this.console = console;
        this.settings = settings;
        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        console.show(new PlayMenu(console, settings, mainMenu));
    }
}
