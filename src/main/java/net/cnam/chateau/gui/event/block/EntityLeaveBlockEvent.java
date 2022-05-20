package net.cnam.chateau.gui.event.block;

import net.cnam.chateau.entity.LivingEntity;

public class EntityLeaveBlockEvent extends BlockEvent {

    public EntityLeaveBlockEvent(LivingEntity entity) {
        super(entity);
    }
}
