package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.LockedDoor;

public class OpenDoorButton extends CButton {
    private final LockedDoor door;
    private final DoorMenu menu;

    public OpenDoorButton(App app, LockedDoor door, DoorMenu menu) {
        super(app, "Utiliser la clé pour ouvrir la porte !");

        this.door = door;
        this.menu = menu;
    }

    @Override
    public void execute() {
        door.setLock(false);
        this.menu.stopDisplay();
    }
}
