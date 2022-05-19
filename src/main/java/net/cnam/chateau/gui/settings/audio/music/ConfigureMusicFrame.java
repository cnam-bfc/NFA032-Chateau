package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CGauge;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureMusicFrame extends CFrame implements DisplayableComponent {

    private boolean display = true;

    public ConfigureMusicFrame(AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super(new CLabel("Configurer le niveau sonore de la musique"), 0, 0);

        CGauge gauge = new ConfigureMusicGauge(settings, menuPlayer);
        CChoices choices = new CChoices(new SelectableComponent[]{
            gauge,
            new CChoices(new SelectableComponent[]{
                new ConfigureMusicOkButton(this, settings, gauge),
                new ConfigureMusicCancelButton(this, settings, menuPlayer)
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
