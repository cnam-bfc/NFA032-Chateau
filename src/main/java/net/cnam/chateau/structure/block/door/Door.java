package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.Block;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class Door extends Block implements BlockListener {

    private final Stage stage;
    private final Room roomOne;
    private final Room roomTwo;

    public Door(Stage stage, Room roomOne, Room roomTwo) {
        this.stage = stage;
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
    }

    @Override
    public String getCharacter() {
        if (isLocked()) {
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
        return false;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (!isLocked() && event.getEntity() instanceof Player player) {
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
