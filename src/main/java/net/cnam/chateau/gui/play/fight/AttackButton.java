package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class AttackButton extends CButton {
    private final Fight fight;

    public AttackButton(App app, Fight fight) {
        super(app, "Attaquer");

        this.fight = fight;
    }

    @Override
    public void execute() {
        fight.attack();
    }
}
