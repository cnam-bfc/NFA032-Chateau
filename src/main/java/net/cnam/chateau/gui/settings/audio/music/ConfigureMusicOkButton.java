package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CSlider;

public class ConfigureMusicOkButton extends CButton {
    private final AppSettings settings;
    private final ConfigureMusicFrame configureMusicFrame;
    private final CSlider gauge;

    public ConfigureMusicOkButton(App app, ConfigureMusicFrame configureMusicFrame, CSlider gauge) {
        super(app, "Valider");

        this.settings = app.getSettings();
        this.configureMusicFrame = configureMusicFrame;
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        configureMusicFrame.stopDisplaying();
        settings.setMusicVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
