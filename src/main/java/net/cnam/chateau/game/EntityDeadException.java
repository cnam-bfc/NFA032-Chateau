package net.cnam.chateau.game;

import net.cnam.chateau.entity.Entity;

public class EntityDeadException extends Exception {
    private final Entity entity;

    public EntityDeadException(Entity entity, String message) {
        super(message);

        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
