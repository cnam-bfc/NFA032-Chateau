package net.cnam.chateau.gui.main.menu.statistics;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Statistic;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;

public class ShowStatButton extends CButton {

    private App app;
    private Statistic stat;
    private StatisticsMenu menu;

    public ShowStatButton(App app, Statistic stat, StatisticsMenu menu, int placement) {
        super(app, ""+placement+". "+stat.getPlayerName() +" "+ stat.getScore());

        this.app = app;
        this.stat = stat;
        this.menu = menu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,
                "Nom du joueur : " + stat.getPlayerName() +
                "\nScore du joueur : " + stat.getScore()+
                "\nBoss vaincu : " + (stat.isBossDefeated()?"Oui":"Non")+
                "\nNombre d'ennemi vaincu : " + stat.getNbEnemyKilled()+
                "\nNombre de pièce visitée : " + stat.getNbRoomsVisited()+
                "\nGraine utilisée : " + stat.getSeed()));
    }
}
