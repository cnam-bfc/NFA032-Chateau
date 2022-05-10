package net.cnam.gui.menu.mainmenu;

import net.cnam.entity.Characteristic;
import net.cnam.entity.Player;
import net.cnam.entity.Sexe;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;
import net.cnam.object.Location;
import net.cnam.structure.Game;

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
