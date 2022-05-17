package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.audio.music.ConfigureMusicFrame;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicButton extends CButton {

    private final Console console;
    private final AppSettings settings;
    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicButton(Console console, AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super("Configurer le volume de la musique");

        this.console = console;
        this.settings = settings;
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        console.show(new ConfigureMusicFrame(settings, menuPlayer));
    }
}
