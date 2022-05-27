package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureSoundEffectsFrame extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConfigureSoundEffectsFrame(App app) {
        super(0, 0, "Configurer le niveau sonore des effets sonores");

        CSlider gauge = new ConfigureSoundEffectsSlider(app);
        CChoices choices = new CChoices(app, new SelectableComponent[]{
                gauge,
                new CChoices(app, new SelectableComponent[]{
                        new ConfigureSoundEffectsOkButton(app, this, gauge),
                        new ConfigureSoundEffectsCancelButton(app, this)
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
