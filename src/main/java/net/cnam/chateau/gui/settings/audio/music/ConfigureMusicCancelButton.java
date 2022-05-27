package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicCancelButton extends CButton {
    private final AppSettings settings;
    private final ConfigureMusicFrame configureMusicFrame;
    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicCancelButton(App app, ConfigureMusicFrame configureMusicFrame, SimpleAudioPlayer menuPlayer) {
        super(app, "Annuler");

        this.settings = app.getSettings();
        this.configureMusicFrame = configureMusicFrame;
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        configureMusicFrame.stopDisplaying();

        try {
            menuPlayer.setVolume(settings.getMusicVolume());
        } catch (Exception ignored) {
        }
    }
}
