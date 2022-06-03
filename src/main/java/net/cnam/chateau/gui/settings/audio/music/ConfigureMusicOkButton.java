package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CSlider;

public class ConfigureMusicOkButton extends QuitComponentButton {
    private final AppSettings settings;
    private final CSlider gauge;

    public ConfigureMusicOkButton(App app, ConfigureMusicFrame configureMusicFrame, CSlider gauge) {
        super(app, configureMusicFrame, "Valider");

        this.settings = app.getSettings();
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        super.execute();

        settings.setMusicVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
