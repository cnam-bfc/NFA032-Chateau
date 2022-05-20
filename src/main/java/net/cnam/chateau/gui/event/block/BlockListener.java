package net.cnam.chateau.gui.event.block;

public interface BlockListener {

    void onEntityEnterBlock(EntityEnterBlockEvent event);

    void onEntityLeaveBlock(EntityLeaveBlockEvent event);
}
