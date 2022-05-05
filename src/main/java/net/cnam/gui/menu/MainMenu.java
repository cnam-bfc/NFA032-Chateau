package net.cnam.gui.menu;

import net.cnam.App;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.gui.component.CButtonsChoices;

public class MainMenu extends CFrame {

    private final CButtonsChoices buttonsChoices;
    private boolean show = false;

    public MainMenu(App app) {
        super(new CLabel("Menu principal"));

        CButton playButton = new CButton("1. Jouer");
        playButton.setExecuteCode(() -> {
            app.getConsole().getContent().remove(MainMenu.this);
            app.startGame();
            app.getConsole().getContent().add(MainMenu.this);
        });
        CButton settingsButton = new CButton("2. Paramètres");
        settingsButton.setExecuteCode(() -> {
            app.getConsole().getContent().remove(MainMenu.this);
            app.getConsole().adjustSize();
            MainMenu.this.setSize(app.getConsole().getLength(), app.getConsole().getHeight());
            app.getConsole().getContent().add(MainMenu.this);
        });
        CButton quitButton = new CButton("3. Quitter");
        quitButton.setExecuteCode(() -> {
            MainMenu.this.show = false;
        });
        this.buttonsChoices = new CButtonsChoices(new CButton[]{playButton, settingsButton, quitButton}, 1);

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
}
