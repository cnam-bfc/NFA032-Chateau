package net.cnam.chateau.gui.main.menu.statistics;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Statistic;
import net.cnam.chateau.gui.component.CButton;

public class ShowStatButton extends CButton {
    private final App app;
    private final Statistic statistic;

    public ShowStatButton(App app, Statistic statistic, int placement) {
        super(app, "" + placement + ". " + statistic.getPlayerName() + " " + statistic.getScore());

        this.app = app;
        this.statistic = statistic;
    }

    @Override
    public void execute() {
        app.getConsole().show(new ViewStatisticMenu(statistic));
    }
}
