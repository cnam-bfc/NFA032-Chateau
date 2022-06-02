package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CTextField;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class CheatMenu extends CFrame implements DisplayableComponent {

    private boolean display = true;
    private CTextField leTexteQueLeMecEcritDansLeCadre;

    public CheatMenu(App app, Game game) {
        super(0, 0, "Menu de triche");

        CChoices choices = new CChoices(app, 1);
        choices.add(leTexteQueLeMecEcritDansLeCadre = new CTextField("Entrez le code de triche", AppSettings.CONSOLE_MIN_LENGTH - 10,""));
        choices.add(new ActivateCheatButton(app, this, game));
        choices.add(new BackButton(app, this));

        this.getContentPane().getComponents().add(choices);

    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stopDisplaying() {
        display = false;
    }

    public CTextField getLeTexteQueLeMecEcritDansLeCadre() {
        return this.leTexteQueLeMecEcritDansLeCadre;
    }
}
