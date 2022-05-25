package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.gui.component.CButton;

public class RunAwayButton extends CButton {
    private final Fight fight;

    public RunAwayButton(Fight fight) {
        super("Fuir");

        this.fight = fight;
    }

    @Override
    public void execute() {
        fight.stop();
    }
}
