package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.direction.Orientation;

public class PlayMenu extends CFrame implements DisplayableComponent {
    private final CTextField playerNameField;
    private final CTextField seedField;
    private boolean display = true;

    public PlayMenu(App app, MainMenu mainMenu) {
        super(0, 0, "Créer une partie");

        this.playerNameField = new CTextField("Nom du joueur", AppSettings.CONSOLE_MIN_LENGTH - 10);
        this.seedField = new CTextField("Graine de la carte à générer (aléatoire si vide)", AppSettings.CONSOLE_MIN_LENGTH - 10);
        CButton okButton = new OkButton(app, mainMenu, this);
        CButton backButton = new BackButton(app, this);
        CChoices actions = new CChoices(app, new SelectableComponent[]{
                okButton,
                backButton
        }, Orientation.HORIZONTAL, 10);

        CChoices choices = new CChoices(app, new SelectableComponent[]{
                playerNameField,
                seedField,
                actions
        }, 1);
        choices.select(actions);

        this.getContentPane().getComponents().add(choices);
    }

    public CTextField getPlayerNameField() {
        return playerNameField;
    }

    public CTextField getSeedField() {
        return seedField;
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
