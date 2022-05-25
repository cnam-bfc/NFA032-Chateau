package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CTextField;
import net.cnam.chateau.gui.dialog.ErrorDialog;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class OkButton extends CButton {
    private final App app;
    private final PlayMenu playMenu;
    private final MainMenu mainMenu;
    private final CTextField seedTextField;

    public OkButton(App app, PlayMenu playMenu, MainMenu mainMenu, CTextField seedTextField) {
        super(app.getSettings(), "Lancer la partie");

        this.app = app;
        this.playMenu = playMenu;
        this.mainMenu = mainMenu;
        this.seedTextField = seedTextField;
    }

    @Override
    public void execute() {
        long seed;
        try {
            seed = Long.parseLong(seedTextField.getText());
        } catch (NumberFormatException ex) {
            app.getConsole().show(new ErrorDialog(ErrorDialog.Type.WARNING, "La seed doit être un nombre !"));
            return;
        }
        playMenu.stopDisplaying();
        SimpleAudioPlayer audioPlayer = mainMenu.getAudioPlayer();
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
        Game game = new Game(app, seed);
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
