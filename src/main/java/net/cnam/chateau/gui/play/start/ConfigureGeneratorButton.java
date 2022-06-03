package net.cnam.chateau.gui.play.start;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.start.generator.GeneratorConfigMenuP1;

public class ConfigureGeneratorButton extends CButton {
    private final App app;
    private final PlayMenu playMenu;

    public ConfigureGeneratorButton(App app, PlayMenu playMenu) {
        super(app, "Paramètres du générateur");

        this.app = app;
        this.playMenu = playMenu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new GeneratorConfigMenuP1(app, playMenu.getGeneratorSettings()));
    }
}
