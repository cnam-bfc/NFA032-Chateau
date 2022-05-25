package net.cnam.chateau.gui.play.door;

import java.util.Random;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.LockedDoor;

public class DestroyDoorButton extends CButton {
    
    private LockedDoor door;
    private Player player;
    private DoorMenu menu;

    public DestroyDoorButton(Player player, LockedDoor door, DoorMenu menu) {
        super("Essayer de detruire la porte !");
        
        this.door = door;
        this.player = player;
        this.menu = menu;
    }
    
    public void execute() {
        if (door.tryDestroy()){
            door.setLock(false);
            // message : vous avez réussi à enfoncer la porte
            
        }
        else {
            int damage = new Random().nextInt(5,11);
            try {
                player.damage(damage);
            } catch (EntityDeadException e) {
            }
            // la porte est trop solide vous vous êtes bléssé
        }
        this.menu.stopDisplay();
    }
    
}
