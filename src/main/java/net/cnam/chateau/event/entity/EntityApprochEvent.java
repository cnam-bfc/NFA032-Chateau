package net.cnam.chateau.event.entity;

import net.cnam.chateau.entity.Entity;

public class EntityApprochEvent {

    private final Entity entity;

    public EntityApprochEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
