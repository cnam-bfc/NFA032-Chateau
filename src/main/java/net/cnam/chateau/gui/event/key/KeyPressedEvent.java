package net.cnam.chateau.gui.event.key;

public class KeyPressedEvent {

    private final int key;

    public KeyPressedEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
