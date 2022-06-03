package net.cnam.chateau.gui.play.fight.sage.puzzle;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class LeavePuzzleButton extends CButton {
    private final PuzzleMenu menu;

    public LeavePuzzleButton(App app, PuzzleMenu menu) {
        super(app, "Répondre plus tard");

        this.menu = menu;
    }

    @Override
    public void execute() {
        this.menu.stopLoopingMode();
    }
}
