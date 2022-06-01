package net.cnam.chateau.gui.play.finish;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;

public class FinishMenu extends CFrame implements DisplayableComponent {
    private final CChoices buttons;
    private final CLabel nameLabel;
    private final CTextField nameField;
    private final QuitButton quitButton;

    private boolean display = true;

    // afficher message de fin "Vous avez réussi à sortir du château !"
    // afficher les scores
    // Changer de nom pour les scores
    // bouton continuer
    public FinishMenu(App app, Game game, boolean won) {
        super(0, 0, "Fin de la partie");

        // Texte
        CLabel result;
        if (won) {
            result = new CLabel("Bravo !\n \nVous avez réussi à sortir du château !");
            result.getColors().add(CColor.BRIGHT_GREEN);
        } else {
            result = new CLabel("Dommage !\n \nVous êtes mort !");
            result.getColors().add(CColor.RED);
        }
        result.getColors().add(CColor.BOLD);
        this.getContentPane().getComponents().add(result);

        // Statistiques
        this.getContentPane().getComponents().add(game.getStatistic());

        // Label pseudo
        this.nameLabel = new CLabel("Entrez un pseudo:");
        this.getContentPane().getComponents().add(nameLabel);

        // Boutons
        buttons = new CChoices(app, 1);
        this.nameField = new CTextField("Pseudo pour enregistrer les statistiques", app.getSettings().getConsoleLength() - 10, game.getPlayer().getName());
        buttons.add(nameField);
        buttons.add(new SaveStatsButton(app, game, this));
        this.quitButton = new QuitButton(app, this);
        buttons.add(quitButton);
        this.getContentPane().getComponents().add(buttons);
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

    public CChoices getButtons() {
        return buttons;
    }

    public CLabel getNameLabel() {
        return nameLabel;
    }

    public CTextField getNameField() {
        return nameField;
    }

    public QuitButton getQuitButton() {
        return quitButton;
    }
}
