package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.bed.BedMenu;

public class Bed extends DecorativeBlock implements BlockListener {
    private final App app;
    private boolean used = false;

    public Bed(App app) {
        super("Bed");

        this.app = app;
    }

    @Override
    public String getCharacter() {
        if (this.used) {
            return CColor.MAGENTA + "B" + CColor.MAGENTA.getForegroundReset();
        } else {
            return CColor.GREEN + "B" + CColor.GREEN.getForegroundReset();
        }
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (!isUsed()) {
            if (event.getEntity() instanceof Player player) {
                app.getConsole().show(new BedMenu(app, player, this));
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
