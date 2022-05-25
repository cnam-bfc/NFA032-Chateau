package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.bed.BedMenu;

public class Bed extends DecorativeBlock implements BlockListener {

    private final Console console;
    private boolean used = false;

    public Bed(Console console) {
        super("Bed");
        this.console = console;
    }

    @Override
    public String getCharacter() {
            if (this.used){
                return CColor.BRIGHT_RED + "B" + CColor.BRIGHT_RED.getForegroundReset();
            }
            else {
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
                console.show(new BedMenu(player, this));
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }

}
