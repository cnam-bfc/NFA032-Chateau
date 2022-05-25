package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.audio.AudioPlayer;
import net.cnam.chateau.gui.component.CButton;

public class ConfigureSoundEffectsCancelButton extends CButton {
    private final ConfigureSoundEffectsFrame configureSoundEffectsFrame;
    private final AppSettings settings;

    public ConfigureSoundEffectsCancelButton(ConfigureSoundEffectsFrame configureSoundEffectsFrame, AppSettings settings) {
        super("Annuler");

        this.configureSoundEffectsFrame = configureSoundEffectsFrame;
        this.settings = settings;
    }

    @Override
    public void execute() {
        configureSoundEffectsFrame.stopDisplaying();
        AudioPlayer.volume = settings.getSoundEffectsVolume();
    }
}
