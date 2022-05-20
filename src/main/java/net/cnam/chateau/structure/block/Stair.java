package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.event.BlockListener;
import net.cnam.chateau.gui.event.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.EntityLeaveBlockEvent;
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
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            // On enlève le joueur de l'étage courant
            stage.getEntities().remove(player);
            if (player.havePet()) {
                stage.getEntities().remove(player.getPet());
            }

            // On ajoute le joueur dans l'autre étage
            player.setStage(otherStair.stage);
            otherStair.stage.getEntities().add(player);
            player.getLocation().setX(otherStair.location.getX());
            player.getLocation().setY(otherStair.location.getY());
            try {
                otherStair.stage.getRoom(otherStair.location).setVisited(true);
            } catch (CoordinatesOutOfBoundsException ex) {
            }
            if (player.havePet()) {
                otherStair.stage.getEntities().add(player.getPet());
                player.getPet().getLocation().setX(otherStair.location.getX());
                player.getPet().getLocation().setY(otherStair.location.getY());
            }
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
