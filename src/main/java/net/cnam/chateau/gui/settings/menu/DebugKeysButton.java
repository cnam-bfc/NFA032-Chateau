package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.console.RawConsoleInput;

import java.io.IOException;

public class DebugKeysButton extends CButton {
    public DebugKeysButton() {
        super("Debug keys");
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
