package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.gui.component.CGauge;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ConfigureSoundEffectsGauge extends CGauge {
    public ConfigureSoundEffectsGauge(AppSettings settings) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, (int) (settings.getSoundEffectsVolume() * 20), 20);
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);

        try {
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(SoundEffect.HOVER.getFilePath());
            audioPlayer.setVolume((float) this.getValue() / this.getMaxValue());
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }
    }
}
