package net.cnam.chateau.gui.event;

public interface BlockListener {

    void onEntityEnterBlock(EntityEnterBlockEvent event);

    void onEntityLeaveBlock(EntityLeaveBlockEvent event);
}
