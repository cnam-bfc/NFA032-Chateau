package net.cnam.chateau.gui;

public interface DisplayableComponent {
    boolean isInFullScreenMode();

    boolean isInLoopingMode();

    void stopLoopingMode();
}
