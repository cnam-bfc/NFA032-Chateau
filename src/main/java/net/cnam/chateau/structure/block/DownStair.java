package net.cnam.chateau.structure.block;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.event.BlockEvent;
import net.cnam.chateau.gui.event.BlockListener;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class DownStair extends Block implements BlockListener {

    private boolean locked = false;
    private UpStair upStair;

    @Override
    public boolean isSolid() {
        return locked;
    }

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "D" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }

    @Override
    public void onEntityEnterBlock(BlockEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            // TODO Faire descendre d'étage le joueur
        }
    }

    @Override
    public void onEntityLeaveBlock(BlockEvent event) {
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public UpStair getUpStair() {
        return upStair;
    }

    public void setUpStair(UpStair upStair) {
        this.upStair = upStair;
    }
}
