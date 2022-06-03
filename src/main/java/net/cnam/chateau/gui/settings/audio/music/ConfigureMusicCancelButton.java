package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicCancelButton extends QuitComponentButton {
    private final AppSettings settings;
    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicCancelButton(App app, ConfigureMusicFrame configureMusicFrame, SimpleAudioPlayer menuPlayer) {
        super(app, configureMusicFrame, "Annuler");

        this.settings = app.getSettings();
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        super.execute();

        try {
            menuPlayer.setVolume(settings.getMusicVolume());
        } catch (Exception ignored) {
        }
    }
}
