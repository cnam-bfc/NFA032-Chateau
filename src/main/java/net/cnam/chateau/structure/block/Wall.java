package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;

/**
 * Class permettant de créer un block Mur (Wall) pour la map.
 */
public class Wall extends Block {

    @Override
    public boolean isSolid() {
        return true;
    }
    
    @Override
    public void execute() {
    }

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_BLACK.getBackground() + ' ' + CColor.BRIGHT_BLACK.getBackgroundReset();
    }
}
