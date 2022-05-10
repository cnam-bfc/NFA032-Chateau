package net.cnam.gui.mainmenu;

import java.io.IOException;
import net.cnam.gui.component.CButton;
import net.cnam.utils.console.RawConsoleInput;

public class DebugKeysButton extends CButton {

    public DebugKeysButton() {
        super("Debug keys");
    }

    @Override
    public void execute() {
        while (true) {
            try {
                System.out.println(RawConsoleInput.read(true));
            } catch (IOException ex) {
            }
        }
    }
}
