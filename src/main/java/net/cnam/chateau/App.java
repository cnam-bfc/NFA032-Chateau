package net.cnam.chateau;

import net.cnam.chateau.audio.AudioFile;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.Console;
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
    private final List<SimpleAudioPlayer> audioPlayers = new LinkedList<>();
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
            stopAllPlayers();
            try {
                SimpleAudioPlayer errorSound = createAudioPlayer(Music.ERROR.getAudioFile());
                errorSound.setVolume(settings.getMusicVolume());
                errorSound.play();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
            }
            console.show(new ErrorDialog(ErrorDialog.Type.EXCEPTION, lines));
            console.finalClear(false);
        }
        audioPlayers.clear();
    }

    public SimpleAudioPlayer createAudioPlayer(AudioFile audioFile) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SimpleAudioPlayer player = new SimpleAudioPlayer(audioFile);
        audioPlayers.add(player);
        return player;
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
}
