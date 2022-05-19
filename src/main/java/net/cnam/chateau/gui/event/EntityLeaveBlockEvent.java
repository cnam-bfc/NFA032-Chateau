package net.cnam.chateau.gui.event;

import net.cnam.chateau.entity.LivingEntity;

public class EntityLeaveBlockEvent extends BlockEvent {

    public EntityLeaveBlockEvent(LivingEntity entity) {
        super(entity);
    }
}
