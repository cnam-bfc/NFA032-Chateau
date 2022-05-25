package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {
    private final PlayMenu playMenu;

    public BackButton(AppSettings settings, PlayMenu playMenu) {
        super(settings, "Retour");

        this.playMenu = playMenu;
    }

    @Override
    public void execute() {
        playMenu.stopDisplaying();
    }
}
