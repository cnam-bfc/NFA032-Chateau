package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.game.event.Puzzle;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class DoorSage extends Door {
    
    private Sage sage;
    private Puzzle puzzle;

    public DoorSage(Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);
        // TODO aller chercher un enigme aléatoire non déjà posé
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

    /**
     * Retourne le caractère qui sera défini sur la map.
     * Si un sage est vivant, présent et l'énigme non résolu affiche un "S" rouge
     * Si le sage est mort, ou énigme résolu, affiche un " " pour montrer l'accessibilité
     * 
     * @return le cractère associé au passage
     */
    @Override
    public String getCharacter() {
        if (isLocked()) {
            return CColor.RED + sage.getCharacter() + CColor.RED.getForegroundReset();
        } else {
            return " ";
        }
    }
}
