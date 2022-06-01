package net.cnam.chateau.gui.play.finish;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class SaveStatsButton extends CButton {
    private final FinishMenu finishMenu;

    public SaveStatsButton(App app, FinishMenu finishMenu) {
        super(app, "Sauvegarder les statistiques");

        this.finishMenu = finishMenu;
    }

    @Override
    public void execute() {
        finishMenu.getButtons().remove(this);
        finishMenu.getButtons().select(finishMenu.getQuitButton());
    }
}
