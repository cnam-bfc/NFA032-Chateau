package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CSlider;

public class ConfigureSoundEffectsOkButton extends QuitComponentButton {
    private final AppSettings settings;
    private final CSlider gauge;

    public ConfigureSoundEffectsOkButton(App app, ConfigureSoundEffectsFrame configureSoundEffectsFrame, CSlider gauge) {
        super(app, configureSoundEffectsFrame, "Valider");

        this.settings = app.getSettings();
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        super.execute();

        settings.setSoundEffectsVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
