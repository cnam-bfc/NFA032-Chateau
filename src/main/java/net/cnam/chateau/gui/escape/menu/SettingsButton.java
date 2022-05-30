package net.cnam.chateau.gui.escape.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.menu.SettingsMenu;

public class SettingsButton extends CButton {
    private final App app;
    private final Game game;

    public SettingsButton(App app, Game game) {
        super(app, "Paramètres");

        this.app = app;
        this.game = game;
    }

    @Override
    public void execute() {
        app.getConsole().show(new SettingsMenu(app, game.getAudioPlayer()));
    }
}
