package net.cnam.chateau.gui.event;

public interface CancelableEvent {

    public abstract boolean isCanceled();

    public abstract void setCanceled(boolean cancel);
}
