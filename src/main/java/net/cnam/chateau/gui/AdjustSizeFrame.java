package net.cnam.chateau.gui;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;

public class AdjustSizeFrame extends CFrame implements DisplayableComponent {
    private final AppSettings settings;
    private boolean sizeAdjusted = false;

    public AdjustSizeFrame(AppSettings settings) {
        super(0, 0, "Réglage des dimensions de la console");

        this.settings = settings;

        CLabel instructions_1 = new CLabel(new String[]{"Veuillez ajustez le cadre pour qu'il soit sur les bords de l'écran",
                "Pour cela vous pouvez utiliser les flèches directionnelles ou les touches zqsd"});
        CLabel instructions_2 = new CLabel("Appuyez sur \"Entrée\" pour valider");

        this.getContentPane().getComponents().add(instructions_1);
        this.getContentPane().getComponents().add(instructions_2);
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        super.onKeyPressed(event);

        int key = event.getKey();

        // 10 = Entrée dans netbeans ; 13 = Entrée dans un terminal
        if (key == 10 || key == 13) {
            sizeAdjusted = true;
            return;
        }

        try {
            Direction direction = DirectionUtils.parseDirection(key, true);
            switch (direction) {
                case LEFT -> settings.setConsoleLength(settings.getConsoleLength() - 1);
                case RIGHT -> settings.setConsoleLength(settings.getConsoleLength() + 1);
                case TOP -> settings.setConsoleHeight(settings.getConsoleHeight() - 1);
                case BOTTOM -> settings.setConsoleHeight(settings.getConsoleHeight() + 1);
            }
        } catch (DirectionNotFoundException ignored) {
        }
    }

    public boolean isSizeAdjusted() {
        return sizeAdjusted;
    }

    @Override
    public boolean isInLoopingMode() {
        return !sizeAdjusted;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
