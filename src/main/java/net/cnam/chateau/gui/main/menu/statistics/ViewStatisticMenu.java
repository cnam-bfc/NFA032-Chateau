package net.cnam.chateau.gui.main.menu.statistics;

import net.cnam.chateau.game.Statistic;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class ViewStatisticMenu extends CFrame implements DisplayableComponent {
    public ViewStatisticMenu(Statistic statistic) {
        super(0, 0, "Statistique");

        this.getContentPane().getComponents().add(statistic);

        this.getContentPane().getComponents().add(new CLabel("Appuyez sur une touche pour continuer..."));
    }

    @Override
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
