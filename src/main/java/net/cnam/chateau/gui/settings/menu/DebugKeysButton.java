package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.console.RawConsoleInput;

import java.io.IOException;

public class DebugKeysButton extends CButton {
    public DebugKeysButton(AppSettings settings) {
        super(settings, "Debug keys");
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
