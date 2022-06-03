package net.cnam.chateau.gui.common;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class OpenComponentButton extends CButton {
    private final App app;
    private final DisplayableComponent component;

    public OpenComponentButton(App app, DisplayableComponent component, String text) {
        super(app, text);

        this.app = app;
        this.component = component;
    }

    public OpenComponentButton(App app, DisplayableComponent component, String text, int length) {
        super(app, text, length);

        this.app = app;
        this.component = component;
    }

    @Override
    public void execute() {
        this.app.getConsole().show(this.component);
    }
}
