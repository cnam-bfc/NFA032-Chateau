package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.gui.component.CSlider;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ConfigureSoundEffectsSlider extends CSlider {
    public ConfigureSoundEffectsSlider(AppSettings settings) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, 1, (int) (settings.getSoundEffectsVolume() * 20), 20, "%PERCENT%");
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
