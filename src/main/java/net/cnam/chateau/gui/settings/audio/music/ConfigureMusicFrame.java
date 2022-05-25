package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureMusicFrame extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConfigureMusicFrame(AppSettings settings, SimpleAudioPlayer menuPlayer) {
        super(0, 0, "Configurer le niveau sonore de la musique");

        CGauge gauge = new ConfigureMusicGauge(settings, menuPlayer);
        CChoices choices = new CChoices(settings, new SelectableComponent[]{
                gauge,
                new CChoices(settings, new SelectableComponent[]{
                        new ConfigureMusicOkButton(settings, this, gauge),
                        new ConfigureMusicCancelButton(settings, this, menuPlayer)
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
