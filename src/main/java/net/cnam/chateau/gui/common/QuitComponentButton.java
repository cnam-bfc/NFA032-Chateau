package net.cnam.chateau.gui.common;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class QuitComponentButton extends CButton {
    private final DisplayableComponent component;

    public QuitComponentButton(App app, DisplayableComponent component, String text) {
        super(app, text);

        this.component = component;
    }

    public QuitComponentButton(App app, DisplayableComponent component, String text, int length) {
        super(app, text, length);

        this.component = component;
    }

    @Override
    public void execute() {
        this.component.stopLoopingMode();
    }
}
