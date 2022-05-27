package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.gui.component.CSlider;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ConfigureSoundEffectsSlider extends CSlider {
    private final App app;

    public ConfigureSoundEffectsSlider(App app) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, 1, (int) (app.getSettings().getSoundEffectsVolume() * 20), 20, "%PERCENT%");

        this.app = app;
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);

        try {
            SimpleAudioPlayer audioPlayer = app.createAudioPlayer(SoundEffect.HOVER);
            audioPlayer.setVolume((float) this.getValue() / this.getMaxValue());
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }
    }
}
