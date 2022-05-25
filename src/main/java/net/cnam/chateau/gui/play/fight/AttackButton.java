package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.gui.component.CButton;

public class AttackButton extends CButton {
    private final Fight fight;

    public AttackButton(Fight fight) {
        super("Attaquer");

        this.fight = fight;
    }

    @Override
    public void execute() {
        fight.attack();
    }
}
