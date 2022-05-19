package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {

    private final PlayMenu playMenu;

    public BackButton(PlayMenu playMenu) {
        super("Retour");

        this.playMenu = playMenu;
    }

    @Override
    public void execute() {
        playMenu.stopDisplaying();
    }
}
