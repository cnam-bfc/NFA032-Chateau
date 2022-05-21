package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class Door_Sage extends Door {
    
    private Sage sage;

    public Door_Sage(Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);
    }

    @Override
    public boolean isLocked() {
        return (sage != null);
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (isLocked()) {
            event.setCanceled(true);
            if (event.getEntity() instanceof Player player) {
                // TODO Afficher menu enigme pour ouvrir la porte
                // Si l'énigme est réussi
                //event.setCanceled(false);
            }
            return;
        }
        super.onEntityEnterBlock(event);
    }

    @Override
    public String getCharacter() {
        if (isLocked()) {
            return CColor.RED + sage.getCharacter() + CColor.RED.getForegroundReset();
        } else {
            return " ";
        }
    }
}
