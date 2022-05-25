package net.cnam.chateau.entity;

public class EntityAlreadyTeleportedException extends Exception {
    public EntityAlreadyTeleportedException(String message) {
        super(message);
    }
}
