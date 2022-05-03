package net.cnam.gui.menu;

import java.io.IOException;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.utils.console.RawConsoleInput;
import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import net.cnam.utils.direction.DirectionUtils;

public class MainMenu extends CFrame {

    private final CButton playButton;
    private final CButton settingsButton;
    private final CButton quitButton;

    public MainMenu() {
        super(new CLabel("Menu principal"));

        playButton = new CButton("1. Jouer");
        playButton.setSelected(true);
        settingsButton = new CButton("2. Paramètres");
        quitButton = new CButton("3. Quitter");

        this.getContent().getContent().add(playButton);
        this.getContent().getContent().add(settingsButton);
        this.getContent().getContent().add(quitButton);
    }

    public void show(Console console) {
        this.setSize(console.getLength(), console.getHeight());
        if (!console.getContent().contains(this)) {
            console.getContent().add(this);
        }
        while (true) {
            console.print();
            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    if (quitButton.isSelected()) {
                        break;
                    } else if (settingsButton.isSelected()) {
                        // TODO Faire le menu settings
                    } else if (playButton.isSelected()) {
                        // TODO Faire le menu jouer
                    }
                }
                Direction direction = DirectionUtils.parseDirection(input);
                switch (direction) {
                    case TOP, LEFT -> {
                        if (quitButton.isSelected()) {
                            quitButton.setSelected(false);
                            settingsButton.setSelected(true);
                        } else if (settingsButton.isSelected()) {
                            settingsButton.setSelected(false);
                            playButton.setSelected(true);
                        } else if (playButton.isSelected()) {
                            playButton.setSelected(false);
                            quitButton.setSelected(true);
                        }
                    }
                    case BOTTOM ,RIGHT -> {
                        if (quitButton.isSelected()) {
                            quitButton.setSelected(false);
                            playButton.setSelected(true);
                        } else if (settingsButton.isSelected()) {
                            settingsButton.setSelected(false);
                            quitButton.setSelected(true);
                        } else if (playButton.isSelected()) {
                            playButton.setSelected(false);
                            settingsButton.setSelected(true);
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            } catch (DirectionNotFoundException ex) {

            }
        }
    }
}
