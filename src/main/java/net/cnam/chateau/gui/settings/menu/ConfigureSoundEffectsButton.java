package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.audio.soundeffects.ConfigureSoundEffectsFrame;

public class ConfigureSoundEffectsButton extends CButton {

    private final Console console;
    private final AppSettings settings;

    public ConfigureSoundEffectsButton(Console console, AppSettings settings) {
        super("Configurer le volume des effects sonores");

        this.console = console;
        this.settings = settings;
    }

    @Override
    public void execute() {
        console.show(new ConfigureSoundEffectsFrame(settings));
    }
}
