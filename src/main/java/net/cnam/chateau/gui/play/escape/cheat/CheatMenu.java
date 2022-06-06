package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CTextField;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.play.escape.EscapeMenu;

public class CheatMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;
    private final CTextField cheatCodeField;

    public CheatMenu(App app, Game game, EscapeMenu escapeMenu) {
        super(0, 0, "Menu de triche");

        CChoices choices = new CChoices(app, 1);
        this.cheatCodeField = new CTextField("Entrez le code de triche", AppSettings.CONSOLE_MIN_LENGTH - 10, "");
        choices.add(cheatCodeField);
        choices.add(new ActivateCheatButton(app, this, game, escapeMenu));
        choices.add(new QuitComponentButton(app, this, "Retour"));

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

    public void stopLoopingMode() {
        display = false;
    }

    public CTextField getCheatCodeField() {
        return this.cheatCodeField;
    }
}
