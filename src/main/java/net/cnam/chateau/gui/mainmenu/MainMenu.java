package net.cnam.chateau.gui.mainmenu;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.FullScreenDisplayableComponent;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CButtons;
import net.cnam.chateau.gui.LoopDisplayableComponent;

public class MainMenu extends CFrame implements LoopDisplayableComponent, FullScreenDisplayableComponent {

    private final CButtons buttonsChoices;
    private boolean display = true;

    public MainMenu(Console console) {
        super(new CLabel("Menu principal"));

        this.buttonsChoices = new CButtons(new CButton[]{
            new PlayButton(console),
            new SettingsButton(console),
            new QuitButton(this),
            new DebugGeneratorButton(),
            new DebugKeysButton()
        }, 1);

        this.getContent().getContent().add(buttonsChoices);
    }

    @Override
    public boolean isDisplayableLoopingMode() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
    }

    @Override
    public boolean isDisplayableFullScreenMode() {
        return true;
    }
}
