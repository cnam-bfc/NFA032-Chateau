package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CSlider;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureMusicFrame extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConfigureMusicFrame(App app, SimpleAudioPlayer menuPlayer) {
        super(0, 0, "Configurer le niveau sonore de la musique");

        CSlider gauge = new ConfigureMusicSlider(app.getSettings(), menuPlayer);

        CChoices buttons = new CChoices(app, Orientation.HORIZONTAL, 10);
        buttons.add(new ConfigureMusicOkButton(app, this, gauge));
        buttons.add(new ConfigureMusicCancelButton(app, this, menuPlayer));

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
