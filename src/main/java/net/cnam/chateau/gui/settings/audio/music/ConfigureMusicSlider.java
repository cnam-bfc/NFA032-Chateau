package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CSlider;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicSlider extends CSlider {
    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicSlider(AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, 1, (int) (settings.getMusicVolume() * 20), 20, "%PERCENT%");

        this.menuPlayer = menuPlayer;
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);

        try {
            menuPlayer.setVolume((float) this.getValue() / this.getMaxValue());
        } catch (Exception ignored) {
        }
    }
}
