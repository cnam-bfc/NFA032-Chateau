package net.cnam.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.cnam.App;
import net.cnam.gui.component.CComponent;
import net.cnam.gui.component.CPanel;
import net.cnam.utils.console.RawConsoleInput;

public class Console extends CPanel {

    public static final int MIN_LENGTH = 80;
    public static final int MIN_HEIGHT = 25;

    public Console() {
        super(MIN_LENGTH, MIN_HEIGHT);
    }

    public void adjustSize(App app) {
        List<CComponent> save = new LinkedList<>(this.getContent());
        this.getContent().clear();

        AdjustSizeFrame adjustSizeFrame = new AdjustSizeFrame();
        adjustSizeFrame.setSize(this.getLength(), this.getHeight());
        this.getContent().add(adjustSizeFrame);

        while (!adjustSizeFrame.isSizeAdjusted() && app.isRunning()) {
            print();
            try {
                int input = RawConsoleInput.read(true);
                app.getConsole().keyPressed(input);
                this.setSize(adjustSizeFrame.getLength(), adjustSizeFrame.getHeight());
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            }
        }

        this.getContent().clear();
        this.getContent().addAll(save);
    }

    public void print() {
        clear();
        System.out.println(this);
    }

    // Méthode pour effacer la console
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // TODO REMOVE THIS
    public void debugKeys() {
        while (true) {
            try {
                System.out.println(RawConsoleInput.read(true));
            } catch (IOException ex) {
            }
        }
    }
}
