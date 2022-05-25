package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureSoundEffectsFrame extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConfigureSoundEffectsFrame(AppSettings settings) {
        super(0, 0, "Configurer le niveau sonore des effets sonores");

        CGauge gauge = new ConfigureSoundEffectsGauge(settings);
        CChoices choices = new CChoices(new SelectableComponent[]{
                gauge,
                new CChoices(new SelectableComponent[]{
                        new ConfigureSoundEffectsOkButton(this, settings, gauge),
                        new ConfigureSoundEffectsCancelButton(this, settings)
                }, Orientation.HORIZONTAL, 10)
        }, 5);

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
