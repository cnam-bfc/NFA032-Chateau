package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.event.block.BlockListener;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;

/**
 * Class permettant de créer un block Mur (Wall) pour la map.
 */
public class Wall extends Block implements BlockListener {

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_BLACK.getBackground() + ' ' + CColor.BRIGHT_BLACK.getBackgroundReset();
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        // On enpêche l'entité de rentrer dans le mur
        event.setCanceled(true);
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
