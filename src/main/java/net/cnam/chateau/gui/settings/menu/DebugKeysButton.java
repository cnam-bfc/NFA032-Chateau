package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.console.RawConsoleInput;

import java.io.IOException;

public class DebugKeysButton extends CButton {
    public DebugKeysButton(App app) {
        super(app, "Debug keys");
    }

    @Override
    public void execute() {
        while (true) {
            try {
                System.out.println(RawConsoleInput.read(true));
            } catch (IOException ignored) {
            }
        }
    }
}
