package net.cnam;

import net.cnam.gui.Console;
import net.cnam.gui.mainmenu.MainMenu;

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
