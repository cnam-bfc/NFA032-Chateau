package net.cnam;

import net.cnam.gui.Console;
import net.cnam.gui.mainmenu.MainMenu;

public class App {

    private final Console console;

    public App() {
        this.console = new Console();
    }

    public void start() {
        console.adjustSize();

        MainMenu mainMenu = new MainMenu(console);
        mainMenu.setSize(console.getLength(), console.getHeight());
        console.show(mainMenu);
    }

    public Console getConsole() {
        return console;
    }
}
