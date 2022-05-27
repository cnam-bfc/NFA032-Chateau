package net.cnam.chateau.gui.settings.audio.music;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Orientation;

public class ConfigureMusicFrame extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConfigureMusicFrame(App app, SimpleAudioPlayer menuPlayer) {
        super(0, 0, "Configurer le niveau sonore de la musique");

        CSlider gauge = new ConfigureMusicSlider(app.getSettings(), menuPlayer);
        CChoices choices = new CChoices(app, new SelectableComponent[]{
                gauge,
                new CChoices(app, new SelectableComponent[]{
                        new ConfigureMusicOkButton(app, this, gauge),
                        new ConfigureMusicCancelButton(app, this, menuPlayer)
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
