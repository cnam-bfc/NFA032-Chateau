package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CGauge;

public class ConfigureSoundEffectsOkButton extends CButton {
    private final AppSettings settings;
    private final ConfigureSoundEffectsFrame configureSoundEffectsFrame;
    private final CGauge gauge;

    public ConfigureSoundEffectsOkButton(AppSettings settings, ConfigureSoundEffectsFrame configureSoundEffectsFrame, CGauge gauge) {
        super(settings, "Valider");

        this.settings = settings;
        this.configureSoundEffectsFrame = configureSoundEffectsFrame;
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        configureSoundEffectsFrame.stopDisplaying();
        settings.setSoundEffectsVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
