package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CGauge;

public class ConfigureMusicOkButton extends CButton {
    private final ConfigureMusicFrame configureMusicFrame;
    private final AppSettings settings;
    private final CGauge gauge;

    public ConfigureMusicOkButton(ConfigureMusicFrame configureMusicFrame, AppSettings settings, CGauge gauge) {
        super("Valider");

        this.configureMusicFrame = configureMusicFrame;
        this.settings = settings;
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        configureMusicFrame.stopDisplaying();
        settings.setMusicVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
