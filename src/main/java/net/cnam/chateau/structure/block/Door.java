package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.event.block.BlockListener;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class Door extends Block implements BlockListener {

    private final Stage stage;
    private final Room roomOne;
    private final Room roomTwo;

    private boolean locked = false;

    public Door(Stage stage, Room roomOne, Room roomTwo) {
        this.stage = stage;
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
    }

    @Override
    public String getCharacter() {
        if (locked) {
            return CColor.RED + "D" + CColor.RED.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Room getRoomOne() {
        return roomOne;
    }

    public Room getRoomTwo() {
        return roomTwo;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (locked) {
            event.setCanceled(true);
            if (event.getEntity() instanceof Player player) {
                // TODO Afficher menu enigme pour ouvrir la porte
                // Si l'énigme est réussi
                //event.setCanceled(false);
            }
            return;
        }

        if (event.getEntity() instanceof Player player) {
            try {
                Room room = stage.getRoom(player.getLocation());
                if (room == roomOne) {
                    roomTwo.setVisible(true);
                } else if (room == roomTwo) {
                    roomOne.setVisible(true);
                }
            } catch (CoordinatesOutOfBoundsException ex) {
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
