package net.cnam.chateau.gui.play.start;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {
    private final PlayMenu playMenu;

    public BackButton(App app, PlayMenu playMenu) {
        super(app, "Retour");

        this.playMenu = playMenu;
    }

    @Override
    public void execute() {
        playMenu.stopDisplaying();
    }
}
