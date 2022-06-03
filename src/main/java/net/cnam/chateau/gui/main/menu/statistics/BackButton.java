package net.cnam.chateau.gui.main.menu.statistics;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {
    private final StatisticsMenu menu;

    public BackButton(App app, StatisticsMenu menu) {
        super(app, "Retour");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopLoopingMode();
    }
}
