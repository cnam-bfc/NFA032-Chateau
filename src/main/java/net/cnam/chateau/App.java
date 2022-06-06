package net.cnam.chateau;

import net.cnam.chateau.audio.Music;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.game.Statistics;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.dialog.DialogType;
import net.cnam.chateau.gui.dialog.ErrorDialog;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class App {
    private final AppSettings settings;
    private final Console console;
    private final Statistics statistics;
    private final List<SimpleAudioPlayer> audioPlayers = new LinkedList<>();
    private Game currentGame;

    /**
     * Constructeur
     */
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
        this.statistics = new Statistics();
    }

    /**
     * Méthode permettant de démarrer l'application.
     */
    public void start() {
        try {
            statistics.loadStatistics();
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
            stopAllPlayers();
            try {
                SimpleAudioPlayer errorSound = createAudioPlayer(Music.ERROR);
                errorSound.setVolume(settings.getMusicVolume());
                errorSound.play();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException |
                     IllegalArgumentException ignored) {
            }
            console.show(new ErrorDialog(DialogType.EXCEPTION, lines));
            console.finalClear(false);
        }
        audioPlayers.clear();
    }

    public SimpleAudioPlayer createAudioPlayer(Music music) throws UnsupportedAudioFileException, LineUnavailableException, IOException, IllegalArgumentException {
        SimpleAudioPlayer player = new SimpleAudioPlayer(music.getFilePath());
        audioPlayers.add(player);
        return player;
    }

    public SimpleAudioPlayer createAudioPlayer(SoundEffect soundEffect) throws UnsupportedAudioFileException, LineUnavailableException, IOException, IllegalArgumentException {
        return new SimpleAudioPlayer(soundEffect.getFilePath());
    }

    public void stopAllPlayers() {
        for (SimpleAudioPlayer player : audioPlayers) {
            player.stop();
        }

        audioPlayers.clear();
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

    public Statistics getStatistics() {
        return statistics;
    }
}
