package net.cnam.chateau.event;

public interface CancelableEvent {

    public abstract boolean isCanceled();

    public abstract void setCanceled(boolean cancel);
}
