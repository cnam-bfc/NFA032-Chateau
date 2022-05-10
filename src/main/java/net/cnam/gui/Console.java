package net.cnam.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.cnam.gui.component.CComponent;
import net.cnam.gui.component.CPanel;
import net.cnam.utils.console.RawConsoleInput;

public class Console extends CPanel {

    public static final int MIN_LENGTH = 80;
    public static final int MIN_HEIGHT = 25;

    public Console() {
        super(MIN_LENGTH, MIN_HEIGHT);
    }

    public void adjustSize() {
        AdjustSizeFrame adjustSizeFrame = new AdjustSizeFrame(this);
        show(adjustSizeFrame);
    }

    public void show(CComponent component) {
        List<CComponent> save = new LinkedList<>(this.getContent());
        this.getContent().clear();

        component.setSize(this.getLength(), this.getHeight());
        this.getContent().add(component);

        boolean continueShowing = false;
        do {
            print();
            try {
                int input = RawConsoleInput.read(true);
                this.onKeyPressed(input);
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            }
            if (component instanceof DisplayableComponent displayableComponent) {
                continueShowing = displayableComponent.isDisplayable();
            }
        } while (continueShowing);

        this.getContent().clear();
        this.getContent().addAll(save);
    }

    private void print() {
        clear();
        System.out.println(this);
    }

    // Méthode pour effacer la console
    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
