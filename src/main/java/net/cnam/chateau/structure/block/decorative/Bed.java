package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.bed.BedMenu;

public class Bed extends DecorativeBlock implements BlockListener {

    private boolean used = false;
    private Console console;

    public Bed(Console console) {
        super("Bed");
        this.console = console;
    }

    @Override
    public String getCharacter() {
        return "B";
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
