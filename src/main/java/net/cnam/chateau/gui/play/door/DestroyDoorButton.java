package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.LockedDoor;

import java.util.Random;

public class DestroyDoorButton extends CButton {
    private final LockedDoor door;
    private final Player player;
    private final DoorMenu menu;

    public DestroyDoorButton(App app, Player player, LockedDoor door, DoorMenu menu) {
        super(app, "Essayer de détruire la porte !");

        this.door = door;
        this.player = player;
        this.menu = menu;
    }

    public void execute() {
        if (door.tryDestroy()) {
            door.setLock(false);
        } else {
            int damage = new Random().nextInt(5, 11);
            try {
                player.damage(damage);
            } catch (EntityDeadException ignored) {
            }
        }
        this.menu.stopLoopingMode();
    }
}
