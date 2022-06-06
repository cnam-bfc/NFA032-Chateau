package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public abstract class Stair extends Block implements PlayerInteractListener {
    private Stage stage;
    private Location location;
    private Stair otherStair;

    public Stair(String name) {
        super(name);
    }


    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        try {
            event.getPlayer().teleport(otherStair.stage, otherStair.location);
        } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ignored) {
        }
    }

    /**
     * Getter permettant de récupérer l'étage ou se situe l'escalier.
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
     * Setter permettant de définir la location de l'escalier.
     *
     * @param location La location (coordonnées) à mettre à l'escalier
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Getter permettant de retourner l'escalier relier à celui-ci.
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
