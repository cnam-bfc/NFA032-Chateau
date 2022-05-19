package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.menu.SettingsMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class SettingsButton extends CButton {

    private final Console console;
    private final AppSettings settings;
    private final SimpleAudioPlayer menuPlayer;

    public SettingsButton(Console console, AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super("Paramètres");

        this.console = console;
        this.settings = settings;
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        console.show(new SettingsMenu(console, settings, menuPlayer));
    }
}
