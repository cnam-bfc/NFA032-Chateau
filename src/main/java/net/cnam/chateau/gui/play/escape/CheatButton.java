package net.cnam.chateau.gui.play.escape;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.gui.play.escape.cheat.CheatMenu;

public class CheatButton extends OpenComponentButton {
    private final App app;
    private final Game game;

    public CheatButton(App app, Game game, EscapeMenu escapeMenu) {
        super(app, new CheatMenu(app, game, escapeMenu), "Triche");

        this.app = app;
        this.game = game;
    }

    @Override
    public void execute() {
        // On avertit le joueur sur les risques du mode cheat
        if (!game.getStatistic().isCheatModeActivated()) {
            app.getConsole().show(new InfoDialog(InfoDialog.Type.WARNING, "Une fois la triche activée, vos statistiques ne seront pas sauvegardées."));
        }

        super.execute();
    }
}
