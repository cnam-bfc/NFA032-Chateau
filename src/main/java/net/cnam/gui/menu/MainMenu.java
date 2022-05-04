package net.cnam.gui.menu;

import net.cnam.App;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.gui.component.CButtonsChoices;

public class MainMenu extends CFrame {

    private final CButtonsChoices menu;

    public MainMenu() {
        super(new CLabel("Menu principal"));

        CButton playButton = new CButton("1. Jouer");
        playButton.setRun(() -> {
            App app = App.getInstance();
            app.getConsole().getContent().remove(app.getMainMenu());
            app.startGame();
            app.getConsole().getContent().add(app.getMainMenu());
        });
        CButton settingsButton = new CButton("2. Paramètres");
        CButton quitButton = new CButton("3. Quitter");
        this.menu = new CButtonsChoices(new CButton[]{playButton, settingsButton, quitButton}, 1);

        this.getContent().getContent().add(menu);
    }

    public void show(App app) {
        Console console = app.getConsole();
        this.setSize(console.getLength(), console.getHeight());
        if (!console.getContent().contains(this)) {
            console.getContent().add(this);
        }
        menu.askKeyboard(console);
        console.getContent().remove(this);
    }
}
