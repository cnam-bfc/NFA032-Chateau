package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CSlider;

public class ConfigureMusicOkButton extends CButton {
    private final AppSettings settings;
    private final ConfigureMusicFrame configureMusicFrame;
    private final CSlider gauge;

    public ConfigureMusicOkButton(AppSettings settings, ConfigureMusicFrame configureMusicFrame, CSlider gauge) {
        super(settings, "Valider");

        this.settings = settings;
        this.configureMusicFrame = configureMusicFrame;
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        configureMusicFrame.stopDisplaying();
        settings.setMusicVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
