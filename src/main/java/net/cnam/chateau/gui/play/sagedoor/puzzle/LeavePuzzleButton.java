package net.cnam.chateau.gui.play.sagedoor.puzzle;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class LeavePuzzleButton extends CButton {
    private final PuzzleMenu menu;

    public LeavePuzzleButton(AppSettings settings, PuzzleMenu menu) {
        super(settings, "Répondre plus tard");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopDisplay();
    }
}
