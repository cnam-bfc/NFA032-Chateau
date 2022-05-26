package net.cnam.chateau.gui.play.sagedoor;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.block.door.SageDoor;

public class AttackButton extends CButton {

    private final SageDoor door;
    private final Player player;
    private final SageDoorMenu menu;

    public AttackButton(AppSettings settings, Player player, SageDoor door, SageDoorMenu menu) {
        super(settings, "Attaquer le sage");

        this.door = door;
        this.player = player;
        this.menu = menu;
    }

    @Override
    public void execute() {
        // TODO faire attaquer le joueur par le sage
        // Fight fight = door.getSage().fight(player);
        this.menu.stopDisplay();
    }
}
