package net.cnam.chateau;

import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.dialog.ErrorDialog;
import net.cnam.chateau.gui.main.menu.MainMenu;

import java.io.File;
import java.io.IOException;

public class App {
    private final AppSettings settings;
    private final Console console;
    private Game currentGame;

    public App() {
        this.settings = new AppSettings();
        try {
            File settingsFile = new File(AppSettings.DEFAULT_FILE_PATH);
            if (settingsFile.exists()) {
                this.settings.load(settingsFile);
            }
        } catch (IOException ignored) {
        }
        this.console = new Console(settings);
        // Initialisation de toutes les listes static
        Sage.initSages(this);
        Pet.createListPet(this);
        Enemy.initSpecialEnemys(this);

    }

    public void start() {
        try {
            MainMenu mainMenu = new MainMenu(this);
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

    public AppSettings getSettings() {
        return settings;
    }

    public Console getConsole() {
        return console;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
