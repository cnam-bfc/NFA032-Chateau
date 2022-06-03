package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.AdjustSizeFrame;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.settings.audio.music.ConfigureMusicFrame;
import net.cnam.chateau.gui.settings.audio.soundeffects.ConfigureSoundEffectsFrame;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class SettingsMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public SettingsMenu(App app, SimpleAudioPlayer menuPlayer) {
        super(0, 0, "Paramètres");

        CButton saveButton = new SaveButton(app, this);

        CChoices choices = new CChoices(app, 1);
        choices.add(new OpenComponentButton(app, new AdjustSizeFrame(app), "Configurer les dimensions de la fenêtre"));
        choices.add(new OpenComponentButton(app, new ConfigureMusicFrame(app, menuPlayer), "Configurer le volume de la musique"));
        choices.add(new OpenComponentButton(app, new ConfigureSoundEffectsFrame(app), "Configurer le volume des effets sonores"));
        choices.add(saveButton);
        //choices.add(new DebugKeysButton(app));
        choices.select(saveButton);

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
