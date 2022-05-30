package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public abstract class Stair extends Block implements BlockListener {

    private Stage stage;
    private Location location;
    private Stair otherStair;

    /**
     * TODO Victor
     * 
     * @param event
     */
    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        try {
            event.getEntity().teleport(otherStair.stage, otherStair.location);
        } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ex) {
        }
    }

    /**
     * TODO Victor
     * 
     * @param event
     */
    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }

    /**
     * Getter permettant de récupéré l'étage ou se situe l'escalier.
     * 
     * @return l'étage de l'escalier.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Setter permettant de définir l'étage de l'escalier.
     *
     * @param stage l'étage de l'escalier.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Getter permettant de retourner la Location (coordonnées) de l'escalier.
     * 
     * @return La location (coordonnées) de l'escalier
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter permetant de définir la location de l'escalier.
     * 
     * @param location La location (coordonnées) à mettre à l'escalier
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Getter permettant de retourner l'ecalier relier à celui-ci.
     * 
     * @return l'escalier lier à celui-ci
     */
    public Stair getOtherStair() {
        return otherStair;
    }

    /**
     * Setter permettant d'attribuer à l'escalier, un autre escalier qui lui sera lié.
     * 
     * @param otherStair Le deuxième escalier
     */
    public void setOtherStair(Stair otherStair) {
        this.otherStair = otherStair;
    }
}
