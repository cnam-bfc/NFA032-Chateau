package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;

public class CheatButton extends CButton {

    private App app;
    private Game game;

    public CheatButton(App app, Game game) {
        super(app, "Triche");

        this.app = app;
        this.game = game;
    }

    @Override
    public void execute() {
        app.getConsole().show(new CheatMenu(app, game));
    }
}
