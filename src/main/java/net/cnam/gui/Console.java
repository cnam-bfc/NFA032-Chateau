package net.cnam.gui;

import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import net.cnam.utils.console.RawConsoleInput;
import net.cnam.utils.direction.DirectionUtils;

public class Console extends CComponent {

    private static final int MIN_LENGTH = 80;
    private static final int MIN_HEIGHT = 25;

    public Console() {
        super(MIN_LENGTH, MIN_HEIGHT);
    }

    public void adjustSize() {
        CComponent[] save = this.getContent().toArray(new CComponent[this.getContent().size()]);
        this.getContent().clear();

        CFrame adjustingWindow = new CFrame(new CLabel("Réglage des dimensions de la console"));
        CLabel instructions_1 = new CLabel(new String[]{"Veuillez ajustez le cadre pour qu'il soit sur les bords de l'écran",
            "Pour cela vous pouvez utiliser les flèches directionnelles ou zqsd"});
        CLabel instructions_2 = new CLabel("Appuyez sur \"Entrée\" pour valider");

        adjustingWindow.add(instructions_1);
        adjustingWindow.add(instructions_2);
        adjustingWindow.setHeight(this.getHeight());
        adjustingWindow.setLength(this.getLength());

        this.add(adjustingWindow);

        while (true) {
            print();
            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    break;
                }
                Direction direction = DirectionUtils.parseDirection(input);
                int newHeight = this.getHeight();
                int newLength = this.getLength();
                switch (direction) {
                    case LEFT -> {
                        if (this.getLength() > MIN_LENGTH) {
                            newLength--;
                        }
                    }
                    case RIGHT -> {
                        newLength++;
                    }
                    case TOP -> {
                        if (this.getHeight() > MIN_HEIGHT) {
                            newHeight--;
                        }
                    }
                    case BOTTOM -> {
                        newHeight++;
                    }
                }
                this.setHeight(newHeight);
                this.setLength(newLength);
                adjustingWindow.setHeight(newHeight);
                adjustingWindow.setLength(newLength);
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            } catch (DirectionNotFoundException ex) {

            }
        }

        this.getContent().clear();
        this.getContent().addAll(Arrays.asList(save));
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

    public void debugKeys() {
        while (true) {
            try {
                System.out.println(RawConsoleInput.read(true));
            } catch (IOException ex) {
            }
        }
    }
}
