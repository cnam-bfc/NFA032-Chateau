package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CGauge;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureSoundEffectsFrame extends CFrame implements DisplayableComponent {

    private boolean display = true;

    public ConfigureSoundEffectsFrame(AppSettings settings) {
        super(new CLabel("Configurer le niveau sonore des  effets sonores"), 0, 0);

        CGauge gauge = new CGauge(AppSettings.CONSOLE_MIN_LENGTH - 10, (int) (settings.getSoundEffectsVolume() * 15), 15);
        CChoices choices = new CChoices(new SelectableComponent[]{
            gauge,
            new CChoices(new SelectableComponent[]{
                new ConfigureSoundEffectsOkButton(this, settings, gauge),
                new ConfigureSoundEffectsCancelButton(this)
            }, Orientation.HORIZONTAL, 10)
        }, 5);

        this.getContent().getContent().add(choices);
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
