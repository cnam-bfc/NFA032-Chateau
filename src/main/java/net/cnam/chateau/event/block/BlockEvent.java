package net.cnam.chateau.event.block;

import net.cnam.chateau.entity.Entity;

public abstract class BlockEvent {

    private final Entity entity;

    public BlockEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
