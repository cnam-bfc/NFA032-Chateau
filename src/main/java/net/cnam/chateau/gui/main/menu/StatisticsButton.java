package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.main.menu.statistics.StatisticsMenu;

public class StatisticsButton extends CButton {

    App app;
    MainMenu menu;

    public StatisticsButton(App app, MainMenu menu) {
        super(app, "Statistiques");

        this.app = app;
        this.menu = menu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new StatisticsMenu(app));
    }
}
