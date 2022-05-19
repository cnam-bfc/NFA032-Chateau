package net.cnam.chateau;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.error.CErrorDialog;
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
            String[] lines = new String[trace.length + 1];
            lines[0] = ex.toString();
            for (int i = 1; i <= trace.length; i++) {
                lines[i] = "   at " + trace[i - 1];
            }
            console.show(new CErrorDialog(CErrorDialog.Type.EXCEPTION, lines));
            console.finalClear(false);
        }
    }
}
