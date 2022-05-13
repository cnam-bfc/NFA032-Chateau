package net.cnam.chateau;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.mainmenu.MainMenu;

public class App {

    private final AppSettings settings;
    private final Console console;

    public App() {
        this.settings = new AppSettings();
        this.console = new Console(settings);
    }

    public void start() {
        MainMenu mainMenu = new MainMenu(console);
        console.show(mainMenu);
    }

    public Console getConsole() {
        return console;
    }
}
