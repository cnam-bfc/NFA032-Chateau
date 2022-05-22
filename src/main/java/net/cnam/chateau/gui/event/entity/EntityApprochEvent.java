package net.cnam.chateau.gui.event.entity;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.utils.direction.Direction;

public abstract class EntityApprochEvent {

    private final Entity entity;
    private final Direction direction;

    public EntityApprochEvent(Entity entity, Direction direction) {
        this.entity = entity;
        this.direction = direction;
    }

    public Entity getEntity() {
        return entity;
    }

    public Direction getFromDirection() {
        return direction;
    }
}
