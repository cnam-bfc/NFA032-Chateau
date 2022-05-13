package net.cnam.chateau.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CComponent;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.utils.console.RawConsoleInput;

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

    public void show(CComponent component) {
        List<CComponent> save = new LinkedList<>(this.getContent());
        this.getContent().clear();

        this.getContent().add(component);

        boolean continueShowing = false;
        do {
            clear();
            if (settings.getConsoleLength() != this.getLength()) {
                this.setLength(settings.getConsoleLength());
            }
            if (settings.getConsoleHeight() != this.getHeight()) {
                this.setHeight(settings.getConsoleHeight());
            }
            for (CComponent comp : this.getContent()) {
                if (comp instanceof FullScreenDisplayableComponent fullScreenComponent) {
                    if (!fullScreenComponent.isDisplayableFullScreenMode()) {
                        continue;
                    }
                    if (this.getLength() != comp.getLength()) {
                        comp.setLength(this.getLength());
                    }
                    if (this.getHeight() != comp.getHeight()) {
                        comp.setHeight(this.getHeight());
                    }
                }
            }
            System.out.print(this);
            try {
                int input = RawConsoleInput.read(true);
                this.onKeyPressed(input);
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            }
            if (component instanceof LoopDisplayableComponent displayableComponent) {
                continueShowing = displayableComponent.isDisplayableLoopingMode();
            }
        } while (continueShowing);

        this.getContent().clear();
        this.getContent().addAll(save);
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
        // Au final non car bug sous windaube
        //System.out.print("\033[?25h");
    }
}
