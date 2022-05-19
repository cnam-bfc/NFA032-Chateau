package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicCancelButton extends CButton {

    private final ConfigureMusicFrame configureMusicFrame;
    private final AppSettings settings;
    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicCancelButton(ConfigureMusicFrame configureMusicFrame, AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super("Annuler");

        this.configureMusicFrame = configureMusicFrame;
        this.settings = settings;
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        configureMusicFrame.stopDisplaying();

        try {
            menuPlayer.setVolume(settings.getMusicVolume());
        } catch (Exception e) {
        }
    }
}
