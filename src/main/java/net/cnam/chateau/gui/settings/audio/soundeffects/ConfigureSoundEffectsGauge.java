package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.audio.AudioPlayer;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.gui.component.CGauge;

public class ConfigureSoundEffectsGauge extends CGauge {

    public ConfigureSoundEffectsGauge(AppSettings settings) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, (int) (settings.getSoundEffectsVolume() * 20), 20);
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);

        AudioPlayer.volume = (float) this.getValue() / this.getMaxValue();
        AudioPlayer.play(SoundEffect.HOVER);
    }
}
