package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.audio.soundeffects.ConfigureSoundEffectsFrame;

public class ConfigureSoundEffectsButton extends CButton {
    private final App app;

    public ConfigureSoundEffectsButton(App app) {
        super(app, "Configurer le volume des effects sonores");

        this.app = app;
    }

    @Override
    public void execute() {
        app.getConsole().show(new ConfigureSoundEffectsFrame(app));
    }
}
