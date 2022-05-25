package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;

public class RunAwayButton extends CButton {
    private final Fight fight;

    public RunAwayButton(AppSettings settings, Fight fight) {
        super(settings, "Fuir");

        this.fight = fight;
    }

    @Override
    public void execute() {
        fight.stop();
    }
}
