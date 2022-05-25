package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class SettingsMenu extends CFrame implements DisplayableComponent {

    private boolean display = true;

    public SettingsMenu(App app, SimpleAudioPlayer menuPlayer) {
        super(new CLabel("Paramètres"), 0, 0);

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
