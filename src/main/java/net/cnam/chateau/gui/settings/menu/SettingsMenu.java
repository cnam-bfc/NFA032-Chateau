package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class SettingsMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public SettingsMenu(App app, SimpleAudioPlayer menuPlayer) {
        super(0, 0, "Paramètres");

        CButton backButton = new SaveButton(app, this);
        CChoices choices = new CChoices(new SelectableComponent[]{
                new ConfigureScreenButton(app.getConsole()),
                new ConfigureMusicButton(app, menuPlayer),
                new ConfigureSoundEffectsButton(app),
                backButton,
                new DebugKeysButton()
        }, 1);
        choices.select(backButton);

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
