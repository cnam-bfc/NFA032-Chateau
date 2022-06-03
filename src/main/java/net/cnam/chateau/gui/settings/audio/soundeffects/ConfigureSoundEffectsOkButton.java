package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CSlider;

public class ConfigureSoundEffectsOkButton extends CButton {
    private final AppSettings settings;
    private final ConfigureSoundEffectsFrame configureSoundEffectsFrame;
    private final CSlider gauge;

    public ConfigureSoundEffectsOkButton(App app, ConfigureSoundEffectsFrame configureSoundEffectsFrame, CSlider gauge) {
        super(app, "Valider");

        this.settings = app.getSettings();
        this.configureSoundEffectsFrame = configureSoundEffectsFrame;
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        configureSoundEffectsFrame.stopLoopingMode();
        settings.setSoundEffectsVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
