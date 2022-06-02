package net.cnam.chateau.gui.play.finish;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.game.Statistic;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CTextField;

public class SaveStatsButton extends CButton {
    private final App app;
    private final Game game;
    private final FinishMenu finishMenu;

    public SaveStatsButton(App app, Game game, FinishMenu finishMenu) {
        super(app, "Sauvegarder les statistiques");

        this.app = app;
        this.game = game;
        this.finishMenu = finishMenu;
    }

    @Override
    public void execute() {
        CTextField nameField = finishMenu.getNameField();

        // Sauvegarde des statistiques
        Statistic statistic = game.getStatistic();
        statistic.setPlayerName(nameField.getText());
        app.getStatistics().addStatistic(statistic);

        // On enlève les boutons
        finishMenu.getContentPane().getComponents().remove(finishMenu.getNameLabel());
        finishMenu.getButtons().remove(nameField);
        finishMenu.getButtons().remove(this);
        finishMenu.getQuitButton().setSelected(true);
    }
}
