package net.cnam.gui;

import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import net.cnam.utils.direction.DirectionUtils;

public class AdjustSizeFrame extends CFrame implements LoopDisplayableComponent {

    private final Console console;
    private boolean sizeAdjusted = false;

    public AdjustSizeFrame(Console console) {
        super();

        this.console = console;

        CLabel title = new CLabel("Réglage des dimensions de la console");
        CLabel instructions_1 = new CLabel(new String[]{"Veuillez ajustez le cadre pour qu'il soit sur les bords de l'écran",
            "Pour cela vous pouvez utiliser les flèches directionnelles ou zqsd"});
        CLabel instructions_2 = new CLabel("Appuyez sur \"Entrée\" pour valider");

        this.setTitle(title);
        this.getContent().getContent().add(instructions_1);
        this.getContent().getContent().add(instructions_2);
    }

    @Override
    public void onKeyPressed(int key) {
        super.onKeyPressed(key);

        // 10 = Entrée dans netbeans ; 13 = Entrée dans un terminal
        if (key == 10 || key == 13) {
            sizeAdjusted = true;
            return;
        }

        try {
            Direction direction = DirectionUtils.parseDirection(key);
            int newHeight = this.getHeight();
            int newLength = this.getLength();
            switch (direction) {
                case LEFT -> {
                    if (this.getLength() > Console.MIN_LENGTH) {
                        newLength--;
                    }
                }
                case RIGHT -> {
                    newLength++;
                }
                case TOP -> {
                    if (this.getHeight() > Console.MIN_HEIGHT) {
                        newHeight--;
                    }
                }
                case BOTTOM -> {
                    newHeight++;
                }
            }
            this.setSize(newLength, newHeight);
            console.setSize(newLength, newHeight);
        } catch (DirectionNotFoundException ex) {
        }
    }

    public boolean isSizeAdjusted() {
        return sizeAdjusted;
    }

    @Override
    public boolean isDisplayable() {
        return !sizeAdjusted;
    }
}
