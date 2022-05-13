package net.cnam.chateau.gui.mainmenu;

import java.io.IOException;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.utils.console.RawConsoleInput;

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
