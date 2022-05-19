package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;

public class DownStair extends Stair {

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "D" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }
}
