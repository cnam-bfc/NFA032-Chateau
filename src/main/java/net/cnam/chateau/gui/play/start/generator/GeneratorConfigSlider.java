package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CSlider;

public class GeneratorConfigSlider extends CSlider {
    private final GeneratorConfigMenu configMenu;

    public GeneratorConfigSlider(GeneratorConfigMenu configMenu, String text) {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10, 2, 0, 0, 0, text + "\n%MIN_VALUE%%SEPARATOR%%VALUE%%SEPARATOR%%MAX_VALUE%");

        this.configMenu = configMenu;
    }

    public void forceValue(int value) {
        super.setValue(value);

        // Fix le bug d'actualisation
        super.render();
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);

        configMenu.update(this);
    }
}
