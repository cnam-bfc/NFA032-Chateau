package net.cnam.chateau.gui.play.start;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;

public class OkButton extends CButton {
    private static final String[] randomPseudos = new String[]{"[720NoScope]Headshotz", "Aloy", "Joel", "Ellie", "Kratos", "Masterchief", "Sam Porter Bridges", "Claire Redfield", "Ada Wong", "Shepard", "Mason", "Capitaine Price", "Steve", "Conor", "Amicia", "Ezio", "Chell", "Tina", "Jesse Faden", "Carl Johnson", "Ulfric Sombrage", "Aerith", "Tifa", "Yuna", "Trevor", "Claudette", "Lara Croft", "Nancy", "Eleven", "Nathan Drake", "Peter Parker"};

    private final App app;
    private final MainMenu mainMenu;
    private final PlayMenu playMenu;

    public OkButton(App app, MainMenu mainMenu, PlayMenu playMenu) {
        super(app, "Lancer la partie");

        this.app = app;
        this.mainMenu = mainMenu;
        this.playMenu = playMenu;
    }

    @Override
    public void execute() {
        String playerName;
        if (playMenu.getPlayerNameField().getText().isBlank()) {
            playerName = randomPseudos[new Random().nextInt(randomPseudos.length)];
        } else {
            playerName = playMenu.getPlayerNameField().getText();
        }
        long seed = 0;
        if (playMenu.getSeedField().getText().isBlank()) {
            seed = new Random().nextLong();
        } else {
            try {
                seed = Long.parseLong(playMenu.getSeedField().getText());
            } catch (NumberFormatException ex) {
                String strSeed = playMenu.getSeedField().getText();
                // Convert seed string to long seed
                for (int i = 0; i < strSeed.length(); i++) {
                    seed += strSeed.charAt(i);
                }
            }
        }
        playMenu.stopLoopingMode();
        SimpleAudioPlayer audioPlayer = mainMenu.getAudioPlayer();
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
        Game game = new Game(app, seed, playerName);
        app.setCurrentGame(game);
        app.getConsole().show(game);
        app.setCurrentGame(null);
        if (audioPlayer != null) {
            try {
                audioPlayer.setVolume(app.getSettings().getMusicVolume());
                audioPlayer.restart();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ignored) {
            }
        }
    }
}
