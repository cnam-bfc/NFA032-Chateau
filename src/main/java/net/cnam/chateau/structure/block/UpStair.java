package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;

public class UpStair extends Stair {

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "U" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }
}
