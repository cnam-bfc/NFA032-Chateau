package net.cnam.chateau.event.block;

public interface BlockListener {

    void onEntityEnterBlock(EntityEnterBlockEvent event);

    void onEntityLeaveBlock(EntityLeaveBlockEvent event);
}
