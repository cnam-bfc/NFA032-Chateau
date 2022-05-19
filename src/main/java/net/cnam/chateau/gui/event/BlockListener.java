package net.cnam.chateau.gui.event;

public interface BlockListener {

    void onEntityEnterBlock(BlockEvent event);

    void onEntityLeaveBlock(BlockEvent event);
}
