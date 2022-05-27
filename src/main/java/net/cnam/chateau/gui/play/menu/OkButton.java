package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.ErrorDialog;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;

public class OkButton extends CButton {
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
        long seed;
        if (playMenu.getSeedField().getText().isBlank()) {
            seed = new Random().nextLong();
        } else {
            try {
                seed = Long.parseLong(playMenu.getSeedField().getText());
            } catch (NumberFormatException ex) {
                app.getConsole().show(new ErrorDialog(ErrorDialog.Type.WARNING, "La seed doit être un nombre !"));
                return;
            }
        }
        if (playMenu.getPlayerNameField().getText().isBlank()) {
            app.getConsole().show(new ErrorDialog(ErrorDialog.Type.WARNING, "Le nom du joueur ne peut être vide !"));
            return;
        }
        playMenu.stopDisplaying();
        SimpleAudioPlayer audioPlayer = mainMenu.getAudioPlayer();
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
        Game game = new Game(app, seed, playMenu.getPlayerNameField().getText());
        app.setCurrentGame(game);
        app.getConsole().show(game);
        app.setCurrentGame(null);
        if (audioPlayer != null) {
            try {
                audioPlayer.restart();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ignored) {
            }
        }
    }
}
