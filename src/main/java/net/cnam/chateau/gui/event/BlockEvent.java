package net.cnam.chateau.gui.event;

import net.cnam.chateau.entity.LivingEntity;

public class BlockEvent {

    private final LivingEntity entity;

    public BlockEvent(LivingEntity entity) {
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }
}
