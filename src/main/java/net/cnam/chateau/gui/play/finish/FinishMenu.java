package net.cnam.chateau.gui.play.finish;

import net.cnam.chateau.App;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class FinishMenu extends CFrame implements DisplayableComponent {

    // afficher message de fin "Vous avez réussi à sortir du château !"
    // afficher les scores
    // Changer de nom pour les scores
    // bouton continuer
    public FinishMenu(App app, Game game, boolean won) {
        super(0, 0, "Fin de la partie");

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

        this.getContentPane().getComponents().add(game.getStatistic());
    }

    @Override
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return false;
    }
}
