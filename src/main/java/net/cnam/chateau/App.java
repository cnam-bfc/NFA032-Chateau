package net.cnam.chateau;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.error.ErrorFrame;
import net.cnam.chateau.gui.main.menu.MainMenu;

public class App {

    private final AppSettings settings;
    private final Console console;

    public App() {
        this.settings = new AppSettings();
        this.console = new Console(settings);
    }

    public void start() {
        try {
            MainMenu mainMenu = new MainMenu(console, settings);
            console.show(mainMenu);
            console.finalClear(true);
        } catch (Exception ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            String[] lines = new String[trace.length + 2];
            lines[0] = ex.toString();
            lines[1] = " ";
            for (int i = 2; i < trace.length + 2; i++) {
                lines[i] = "   at " + trace[i - 2];
            }
            console.show(new ErrorFrame(ErrorFrame.Type.EXCEPTION, lines), false);
            console.finalClear(false);
        }
    }
}
