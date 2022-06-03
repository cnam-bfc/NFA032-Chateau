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

    /**
     * Constructeur de la classe Door.
     *
     * @param stage     L'étage sur lequel se trouve la porte.
     * @param roomOne   La première pièce reliée à la porte
     * @param roomTwo   La seconde pièce reliée à la porte
     */
    public Door(Stage stage, Room roomOne, Room roomTwo) {
        super("Porte");

        this.stage = stage;
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
    }

    /**
     * Méthode permettant de retourner le block qui sera affiché sur la carte
     * @return Le block qui sera affiché sur la carte
     */
    @Override
    public String getCharacter() {
        if (isLocked()) {
            return CColor.BRIGHT_RED + "D" + CColor.BRIGHT_RED.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }

    /**
     * Méthode d'accès à l'étage de la porte
     * @return L'étage de la porte
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Méthode d'accès à la première pièce reliée à la porte
     * @return La première pièce reliée à la porte
     */
    public Room getRoomOne() {
        return roomOne;
    }

    /**
     * Méthode d'accès à la seconde pièce reliée à la porte
     * @return La seconde pièce reliée à la porte
     */
    public Room getRoomTwo() {
        return roomTwo;
    }

    /**
     * Méthode permettant de savoir si la porte est ouverte ou non
     * @return true si la porte est ouverte, false sinon
     */
    public boolean isLocked() {
        return false;
    }

    /**
     * // TODO Victor
     * @param event
     */
    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (isLocked()) {
            event.setCanceled(true);
        } else if (event.getEntity() instanceof Player player) {
            try {
                Room room = stage.getRoom(player.getLocation());
                if (room == roomOne) {
                    roomTwo.setVisible(true);
                } else if (room == roomTwo) {
                    roomOne.setVisible(true);
                }
            } catch (CoordinatesOutOfBoundsException ignored) {
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
