package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.settings.audio.music.ConfigureMusicFrame;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class ConfigureMusicButton extends CButton {
    private final App app;
    private final SimpleAudioPlayer menuPlayer;

    public ConfigureMusicButton(App app, SimpleAudioPlayer menuPlayer) {
        super(app, "Configurer le volume de la musique");

        this.app = app;
        this.menuPlayer = menuPlayer;
    }

    @Override
    public void execute() {
        app.getConsole().show(new ConfigureMusicFrame(app, menuPlayer));
    }
}
