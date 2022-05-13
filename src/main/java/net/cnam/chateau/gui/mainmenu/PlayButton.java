package net.cnam.chateau.gui.mainmenu;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sexe;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.game.Game;

public class PlayButton extends CButton {

    private final Console console;

    public PlayButton(Console console) {
        super("1. Jouer");

        this.console = console;
    }

    @Override
    public void execute() {
        Player player = new Player(Sexe.MASCULIN, new Characteristic(100, 100, 100, 100, 100), new Location(1, 1), "");
        Game game = new Game(player);
        console.show(game);
    }
}
