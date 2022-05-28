package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.LockedDoor;

public class UseObjectButton extends CButton {
    private final Player player;
    private final LockedDoor door;
    private final DoorMenu menu;

    public UseObjectButton(App app, Player player, LockedDoor door, DoorMenu menu) {
        super(app, "Utiliser : " + player.getKey().getName());

        this.player = player;
        this.door = door;
        this.menu = menu;
    }

    @Override
    public void execute() {
        if (player.getKey() == door.getKey()) {
            door.setLock(false);
            player.setKey(null);
            // TODO Message porte ouverte, objet détruit
            this.menu.stopDisplay();
        } else {
            // TODO Message : Ce n'est pas une clé !
        }
    }
}
