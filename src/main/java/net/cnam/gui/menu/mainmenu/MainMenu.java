package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.gui.component.CButtons;

public class MainMenu extends CFrame {

    private final CButtons buttonsChoices;

    public MainMenu(App app) {
        super(new CLabel("Menu principal"));

        this.buttonsChoices = new CButtons(new CButton[]{new PlayButton(app), new SettingsButton(app), new QuitButton(app)}, 1);

        this.getContent().getContent().add(buttonsChoices);
    }
}
