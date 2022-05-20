package net.cnam.chateau.gui.event.key;

public class KeyEvent {

    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
