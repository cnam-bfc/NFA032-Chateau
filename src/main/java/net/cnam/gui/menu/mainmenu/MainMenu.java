package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.gui.component.CButtons;

public class MainMenu extends CFrame {

    private final CButtons buttonsChoices;
    private boolean show = false;

    public MainMenu(App app) {
        super(new CLabel("Menu principal"));

        this.buttonsChoices = new CButtons(new CButton[]{new PlayButton(app), new SettingsButton(app), new QuitButton(app)}, 1);

        this.getContent().getContent().add(buttonsChoices);
    }

    public void show(App app) {
        Console console = app.getConsole();
        this.setSize(console.getLength(), console.getHeight());
        if (!console.getContent().contains(this)) {
            console.getContent().add(this);
        }
        show = true;
        while (show) {
            buttonsChoices.askKeyboard(console);
        }
        console.getContent().remove(this);
    }

    public void stopShowing() {
        this.show = false;
    }
}
