package net.cnam.chateau.gui.play.finish;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.game.Statistic;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CTextField;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

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
        int nbRoomsVisited = 0;
        int nbRoomsCastle = 0;
        Stage[] stages = game.getCastle().getStages();
        for (Stage stage : stages) {
            Room[] rooms = stage.getRooms();
            for (Room room : rooms) {
                nbRoomsCastle += 1;
                if (room.isVisible()) {
                    nbRoomsVisited += 1;
                }
            }
        }
        Statistic statistic = game.getStatistic();
        statistic.setPlayerName(nameField.getText());
        statistic.setNbRoomsVisited(nbRoomsVisited);
        statistic.setNbRoomsCastle(nbRoomsCastle);
        statistic.calculScore();
        app.getStatistics().addStatistic(statistic);

        // On enlève les boutons
        finishMenu.getButtons().remove(nameField);
        finishMenu.getButtons().remove(this);
        finishMenu.getQuitButton().setSelected(true);
    }
}
