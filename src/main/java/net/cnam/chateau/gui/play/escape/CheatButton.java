package net.cnam.chateau.gui.play.escape;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.DialogType;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.gui.play.escape.cheat.CheatMenu;

public class CheatButton extends CButton {
    private final App app;
    private final Game game;
    private final EscapeMenu escapeMenu;

    public CheatButton(App app, Game game, EscapeMenu escapeMenu) {
        super(app, "Triche");

        this.app = app;
        this.game = game;
        this.escapeMenu = escapeMenu;
    }

    @Override
    public void execute() {
        if (!game.getStatistic().isCheatModeActivated()) {
            app.getConsole().show(new InfoDialog(DialogType.WARNING, "Une fois la triche activée, vos statistiques ne seront pas sauvegardées."));
        }
        app.getConsole().show(new CheatMenu(app, game, escapeMenu));
    }
}
