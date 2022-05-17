package net.cnam.chateau.gui.play.menu;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sexe;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class OkButton extends CButton {

    private final Console console;
    private final AppSettings settings;
    private final PlayMenu playMenu;
    private final MainMenu mainMenu;

    public OkButton(Console console, AppSettings settings, PlayMenu playMenu, MainMenu mainMenu) {
        super("Lancer la partie");

        this.console = console;
        this.settings = settings;
        this.playMenu = playMenu;
        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        playMenu.stopDisplaying();
        SimpleAudioPlayer audioPlayer = mainMenu.getAudioPlayer();
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
        Player player = new Player(Sexe.MASCULIN, new Characteristic(100, 100, 100, 100, 100), new Location(1, 1), "");
        Game game = new Game(settings, player);
        console.show(game);
        if (audioPlayer != null) {
            try {
                audioPlayer.restart();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            }
        }
    }
}
