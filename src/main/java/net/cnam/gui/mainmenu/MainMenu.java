package net.cnam.gui.mainmenu;

import net.cnam.gui.Console;
import net.cnam.gui.DisplayableComponent;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.gui.component.CButtons;

public class MainMenu extends CFrame implements DisplayableComponent {

    private final CButtons buttonsChoices;
    private boolean display = true;

    public MainMenu(Console console) {
        super(new CLabel("Menu principal"));

        this.buttonsChoices = new CButtons(new CButton[]{
            new PlayButton(console),
            new SettingsButton(console, this),
            new QuitButton(this),
            new DebugGeneratorButton(),
            new DebugKeysButton()
        }, 1);

        this.getContent().getContent().add(buttonsChoices);
    }

    @Override
    public boolean isDisplayable() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
    }
}
