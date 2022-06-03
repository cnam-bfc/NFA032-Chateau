package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class SettingsMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public SettingsMenu(App app, SimpleAudioPlayer menuPlayer) {
        super(0, 0, "Paramètres");

        CButton saveButton = new SaveButton(app, this);

        CChoices choices = new CChoices(app, 1);
        choices.add(new ConfigureScreenButton(app));
        choices.add(new ConfigureMusicButton(app, menuPlayer));
        choices.add(new ConfigureSoundEffectsButton(app));
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
