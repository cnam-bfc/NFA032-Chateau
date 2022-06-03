package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class ConfigureSoundEffectsCancelButton extends CButton {
    private final ConfigureSoundEffectsFrame configureSoundEffectsFrame;

    public ConfigureSoundEffectsCancelButton(App app, ConfigureSoundEffectsFrame configureSoundEffectsFrame) {
        super(app, "Annuler");

        this.configureSoundEffectsFrame = configureSoundEffectsFrame;
    }

    @Override
    public void execute() {
        configureSoundEffectsFrame.stopLoopingMode();
    }
}
