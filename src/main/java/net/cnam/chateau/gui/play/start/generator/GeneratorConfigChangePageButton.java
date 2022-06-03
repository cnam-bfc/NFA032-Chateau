package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class GeneratorConfigChangePageButton extends OpenComponentButton {
    private final DisplayableComponent menu;

    public GeneratorConfigChangePageButton(App app, DisplayableComponent menu, DisplayableComponent newMenu, String text) {
        super(app, newMenu, text);

        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stopLoopingMode();

        super.execute();
    }
}
