package net.cnam.gui.menu.mainmenu;

import net.cnam.App;
import net.cnam.entity.Characteristic;
import net.cnam.entity.Personage;
import net.cnam.entity.Sexe;
import net.cnam.gui.component.CButton;
import net.cnam.object.Location;
import net.cnam.structure.Game;

public class PlayButton extends CButton {

    private final App app;

    public PlayButton(App app) {
        super("1. Jouer");

        this.app = app;
    }

    @Override
    public void execute() {
        MainMenu mainMenu = app.getMainMenu();
        app.getConsole().getContent().remove(mainMenu);
        app.setCurrentGame(new Game(new Personage(null, "", Sexe.MASCULIN, 100, 100, new Characteristic(100, 100, 100), 'P', new Location(1, 1))));
        app.getCurrentGame().start(app.getConsole());
        app.getConsole().getContent().add(mainMenu);
    }
}
