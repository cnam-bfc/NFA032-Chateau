package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.escape.EscapeMenu;

public class CheatButton extends CButton {

    private App app;
    private Game game;
private EscapeMenu escapeMenu;

    public CheatButton(App app, Game game, EscapeMenu escapeMenu) {
        super(app, "Triche");

        this.app = app;
        this.game = game;
        this.escapeMenu = escapeMenu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new CheatMenu(app, game, escapeMenu));
    }
}
