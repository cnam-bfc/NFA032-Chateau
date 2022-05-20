package net.cnam.chateau.gui.event.block;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.gui.event.CancelableEvent;

public class EntityEnterBlockEvent extends BlockEvent implements CancelableEvent {

    private boolean cancel = false;

    public EntityEnterBlockEvent(LivingEntity entity) {
        super(entity);
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
