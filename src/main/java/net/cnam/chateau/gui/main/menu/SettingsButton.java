package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.menu.SettingsMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class SettingsButton extends CButton {
    private final App app;
    private final SimpleAudioPlayer menuPlayer;

    public SettingsButton(App app, SimpleAudioPlayer menuPlayer) {
        super("Paramètres");

        this.app = app;
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        app.getConsole().show(new SettingsMenu(app, menuPlayer));
    }
}
