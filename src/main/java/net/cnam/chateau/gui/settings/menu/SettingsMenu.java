package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CButtons;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;

public class SettingsMenu extends CFrame implements DisplayableComponent {

    private final CButtons buttonsChoices;
    private boolean display = true;

    public SettingsMenu(Console console) {
        super(new CLabel("Paramètres"));

        this.buttonsChoices = new CButtons(new CButton[]{
            new ConfigureScreenButton(console),
            new BackButton(this),
            new DebugGeneratorButton(),
            new DebugKeysButton()
        }, 1);

        this.getContent().getContent().add(buttonsChoices);
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
