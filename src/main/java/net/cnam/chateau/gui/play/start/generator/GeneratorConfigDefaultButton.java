package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.gui.component.CButton;

public class GeneratorConfigDefaultButton extends CButton {
    private final GeneratorSettings generatorSettings;
    private final GeneratorConfigMenu menu;

    public GeneratorConfigDefaultButton(App app, GeneratorSettings generatorSettings, GeneratorConfigMenu menu) {
        super(app, "Par défaut");

        this.generatorSettings = generatorSettings;
        this.menu = menu;
    }

    @Override
    public void execute() {
        generatorSettings.reset();
        menu.update();
    }
}
