package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class ConfigureSoundEffectsCancelButton extends CButton {
    private final ConfigureSoundEffectsFrame configureSoundEffectsFrame;

    public ConfigureSoundEffectsCancelButton(AppSettings settings, ConfigureSoundEffectsFrame configureSoundEffectsFrame) {
        super(settings, "Annuler");

        this.configureSoundEffectsFrame = configureSoundEffectsFrame;
    }

    @Override
    public void execute() {
        configureSoundEffectsFrame.stopDisplaying();
    }
}
