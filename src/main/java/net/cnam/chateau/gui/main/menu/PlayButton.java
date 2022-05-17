package net.cnam.chateau.gui.main.menu;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sexe;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.game.Game;

public class PlayButton extends CButton {

    private final Console console;
    private final AppSettings settings;
    private final MainMenu mainMenu;

    public PlayButton(Console console, AppSettings settings, MainMenu mainMenu) {
        super("1. Jouer");

        this.console = console;
        this.settings = settings;
        this.mainMenu = mainMenu;
    }

    @Override
    public void execute() {
        try {
            mainMenu.getAudioPlayer().stop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException ex) {
        }
        Player player = new Player(Sexe.MASCULIN, new Characteristic(100, 100, 100, 100, 100), new Location(1, 1), "");
        Game game = new Game(settings, player);
        console.show(game);
    }
}
