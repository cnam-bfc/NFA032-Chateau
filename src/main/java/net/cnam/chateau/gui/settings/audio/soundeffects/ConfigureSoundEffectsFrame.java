package net.cnam.chateau.gui.settings.audio.soundeffects;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CSlider;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureSoundEffectsFrame extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConfigureSoundEffectsFrame(App app) {
        super(0, 0, "Configurer le niveau sonore des effets sonores");

        CSlider gauge = new ConfigureSoundEffectsSlider(app);

        CChoices buttons = new CChoices(app, Orientation.HORIZONTAL, 10);
        buttons.add(new ConfigureSoundEffectsOkButton(app, this, gauge));
        buttons.add(new QuitComponentButton(app, this, "Annuler"));

        CChoices choices = new CChoices(app, 5);
        choices.add(gauge);
        choices.add(buttons);

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopLoopingMode() {
        display = false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
