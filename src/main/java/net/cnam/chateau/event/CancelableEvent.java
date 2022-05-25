package net.cnam.chateau.event;

public interface CancelableEvent {
    boolean isCanceled();

    void setCanceled(boolean cancel);
}
