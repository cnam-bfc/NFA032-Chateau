package net.cnam.chateau;

import java.io.File;
import java.io.IOException;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.error.ErrorDialog;
import net.cnam.chateau.gui.main.menu.MainMenu;

public class App {

    private final AppSettings settings;
    private final Console console;

    public App() {
        this.settings = new AppSettings();
        try {
            File settingsFile = new File(AppSettings.DEFAULT_FILE_PATH);
            if (settingsFile.exists()) {
                this.settings.load(settingsFile);
            }
        } catch (IOException ex) {
        }
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
            console.show(new ErrorDialog(ErrorDialog.Type.EXCEPTION, lines));
            console.finalClear(false);
        }
    }
}
