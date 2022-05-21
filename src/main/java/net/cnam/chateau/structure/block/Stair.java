package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.gui.event.block.BlockListener;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public abstract class Stair extends Block implements BlockListener {

    private boolean locked = false;
    private Stage stage;
    private Location location;
    private Stair otherStair;

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        try {
            event.getEntity().teleport(otherStair.stage, otherStair.location);
        } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ex) {
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Stair getOtherStair() {
        return otherStair;
    }

    public void setOtherStair(Stair otherStair) {
        this.otherStair = otherStair;
    }
}
