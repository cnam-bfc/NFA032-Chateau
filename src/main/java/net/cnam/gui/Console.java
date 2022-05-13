package net.cnam.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.cnam.AppSettings;
import net.cnam.gui.component.CComponent;
import net.cnam.gui.component.CPanel;
import net.cnam.utils.console.RawConsoleInput;

public class Console extends CPanel {

    private final AppSettings settings;

    public Console(AppSettings settings) {
        super(settings.getConsoleLength(), settings.getConsoleHeight());

        this.settings = settings;
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
            print();
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
