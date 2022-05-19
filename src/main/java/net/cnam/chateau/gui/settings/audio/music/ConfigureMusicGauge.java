package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CGauge;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicGauge extends CGauge {

    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicGauge(AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, (int) (settings.getMusicVolume() * 15), 15);

        this.menuPlayer = menuPlayer;
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);

        try {
            menuPlayer.setVolume((float) this.getValue() / this.getMaxValue());
        } catch (Exception e) {
        }
    }
}
