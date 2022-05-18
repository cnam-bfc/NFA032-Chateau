package net.cnam.chateau.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CComponent;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.utils.console.RawConsoleInput;
import net.cnam.chateau.utils.direction.Direction;

// Sources ANSI codes:
// https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
// https://askubuntu.com/questions/558280/changing-colour-of-text-and-background-of-terminal
// https://en.wikipedia.org/wiki/ANSI_escape_code
public class Console extends CPanel {

    private final AppSettings settings;

    public Console(AppSettings settings) {
        super(0, 0);

        this.settings = settings;

        // Rendre le curseur invisible
        System.out.print("\033[?25l");
    }

    public void adjustSize() {
        AdjustSizeFrame adjustSizeFrame = new AdjustSizeFrame(settings);
        show(adjustSizeFrame);
    }

    public void show(DisplayableComponent displayableComponent) {
        if (displayableComponent instanceof CComponent component) {
            List<CComponent> save = new LinkedList<>(this.getContent());
            this.getContent().clear();

            this.getContent().add(component);

            boolean continueShowing;
            do {
                clear();
                if (settings.getConsoleLength() != this.getLength()) {
                    this.setLength(settings.getConsoleLength());
                }
                if (settings.getConsoleHeight() != this.getHeight()) {
                    this.setHeight(settings.getConsoleHeight());
                }
                for (CComponent comp : this.getContent()) {
                    if (!displayableComponent.isInFullScreenMode()) {
                        continue;
                    }
                    if (this.getLength() != comp.getLength()) {
                        comp.setLength(this.getLength());
                    }
                    if (this.getHeight() != comp.getHeight()) {
                        comp.setHeight(this.getHeight());
                    }
                }
                System.out.print(this);
                try {
                    int input = RawConsoleInput.read(true);

                    // Si un caractère d'échappemment est entré
                    if (input == 27 && RawConsoleInput.read(true) == 91) {
                        switch (RawConsoleInput.read(true)) {
                            // 65 = Flèche du haut
                            case 65 -> {
                                input = 57416;
                            }
                            // 66 = Flèche du bas
                            case 66 -> {
                                input = 57424;
                            }
                            // 68 = Flèche de gauche
                            case 68 -> {
                                input = 57419;
                            }
                            // 67 = Flèche de droite
                            case 67 -> {
                                input = 57421;
                            }
                        }
                    }

                    // On notifie les éléments graphiques de la touche entrée
                    this.onKeyPressed(input);
                } catch (IOException ex) {
                    System.out.println("ERREUR");
                    System.exit(1);
                }
                continueShowing = displayableComponent.isInLoopingMode();
            } while (continueShowing);

            this.getContent().clear();
            this.getContent().addAll(save);
        }
    }

    // Méthode pour effacer la console
    private void clear() {
        for (int i = 0; i < this.getHeight() - 1; i++) {
            // On déplace le curseur sur la ligne au dessus
            System.out.print("\033[F");
        }
        // On efface le terminal à partir du curseur
        System.out.print("\033[0J");
    }

    public void finalClear() {
        clear();

        // Rendre le curseur visible
        System.out.print("\033[?25h");
    }
}
