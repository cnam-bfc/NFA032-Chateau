package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.LockedDoor;

public class UseObjectButton extends CButton {
    
    private final Player player;
    private final LockedDoor door;
    private final DoorMenu menu;

    public UseObjectButton(Player player, LockedDoor door, DoorMenu menu) {
        super("Utiliser : " + player.getItem().getName());
        
        this.player = player;
        this.door = door;
        this.menu = menu;
    }
    
    @Override
    public void execute() {
        if (player.getItem() == door.getKey()) {
            door.setLock(false);
            player.setItem(null);
            // Todo message porte ouverte, objet détruit
            this.menu.stopDisplay();
        } else {
            // Message : Ce n'est pas une clé !
        }
    }
    
}
