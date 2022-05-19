package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.event.BlockListener;
import net.cnam.chateau.gui.event.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.EntityLeaveBlockEvent;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class UpStair extends Block implements BlockListener {

    private boolean locked = false;
    private DownStair downStair;

    @Override
    public boolean isSolid() {
        return locked;
    }

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "U" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            // TODO Faire monter d'étage le joueur
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

    public DownStair getDownStair() {
        return downStair;
    }

    public void setDownStair(DownStair downStair) {
        this.downStair = downStair;
    }
}
