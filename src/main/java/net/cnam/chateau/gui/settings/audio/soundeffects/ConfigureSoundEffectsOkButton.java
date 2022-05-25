package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CGauge;

public class ConfigureSoundEffectsOkButton extends CButton {
    private final ConfigureSoundEffectsFrame configureSoundEffectsFrame;
    private final AppSettings settings;
    private final CGauge gauge;

    public ConfigureSoundEffectsOkButton(ConfigureSoundEffectsFrame configureSoundEffectsFrame, AppSettings settings, CGauge gauge) {
        super("Valider");

        this.configureSoundEffectsFrame = configureSoundEffectsFrame;
        this.settings = settings;
        this.gauge = gauge;
    }

    @Override
    public void execute() {
        configureSoundEffectsFrame.stopDisplaying();
        settings.setSoundEffectsVolume((float) gauge.getValue() / gauge.getMaxValue());
    }
}
