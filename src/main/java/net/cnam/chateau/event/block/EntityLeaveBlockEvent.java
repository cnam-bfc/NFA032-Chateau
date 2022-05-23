package net.cnam.chateau.event.block;

import net.cnam.chateau.entity.Entity;

public class EntityLeaveBlockEvent extends BlockEvent {

    public EntityLeaveBlockEvent(Entity entity) {
        super(entity);
    }
}
