package net.cnam.chateau.gui.main.menu.statistics;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Statistic;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.DisplayableComponent;

import java.util.List;

public class StatisticsMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public StatisticsMenu(App app) {
        super(0, 0, "Statistiques");

        List<Statistic> stats = app.getStatistics().getStatistics();
        if (stats.isEmpty()) {
            this.getContentPane().getComponents().add(new CLabel("Aucune statistique répertoriée !"));
        }

        CChoices choices = new CChoices(app, 1);

        for (int i = 0; i < stats.size(); i++) {
            choices.add(new ShowStatButton(app, stats.get(i), i + 1));
        }

        choices.add(new QuitComponentButton(app, this, "Retour"));

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopLoopingMode() {
        display = false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
