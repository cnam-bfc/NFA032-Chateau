package net.cnam.chateau.event.entity;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.event.CancelableEvent;

public class EntityApproachEvent implements CancelableEvent {
    private final Entity entity;

    private boolean cancel = false;

    public EntityApproachEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }
}
