package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class RunAwayButton extends CButton {
    private final Fight fight;

    public RunAwayButton(App app, Fight fight) {
        super(app, "Fuir");

        this.fight = fight;
    }

    @Override
    public void execute() {
        fight.stopLoopingMode();
    }
}
