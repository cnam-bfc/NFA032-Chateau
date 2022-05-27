package net.cnam.chateau.gui.play.door.sage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.block.door.SageDoor;

public class AttackButton extends CButton {

    private final SageDoor door;
    private final Player player;
    private final SageDoorMenu menu;

    public AttackButton(App app, Player player, SageDoor door, SageDoorMenu menu) {
        super(app, "Attaquer le sage");

        this.door = door;
        this.player = player;
        this.menu = menu;
    }

    @Override
    public void execute() {
        Fight fight = door.getSage().fight(player);
        if (fight.isOver()) {
            door.setSage(null);
        }
        this.menu.stopDisplay();
    }
}
