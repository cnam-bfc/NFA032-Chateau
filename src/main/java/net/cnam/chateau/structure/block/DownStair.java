package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;

public class DownStair extends Stair {

    /**
     * Redéfinition de la méthode permettant d'afficher un caractère sur la carte.
     * 
     * @return un String correspondant à "D" en jaune
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "D" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }
}
