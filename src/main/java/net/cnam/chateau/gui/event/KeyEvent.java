package net.cnam.chateau.gui.event;

public class KeyEvent {

    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
