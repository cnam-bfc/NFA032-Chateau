package net.cnam.chateau.gui.component;

public interface DisplayableComponent {
    boolean isInFullScreenMode();

    boolean isInLoopingMode();

    void stopLoopingMode();
}
