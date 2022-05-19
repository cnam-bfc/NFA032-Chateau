package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class DownStair extends Block {

    private boolean locked = false;
    private UpStair upStair;

    @Override
    public boolean isSolid() {
        return locked;
    }
    
    @Override
    public void execute() {
    }

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "D" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public UpStair getUpStair() {
        return upStair;
    }

    public void setUpStair(UpStair upStair) {
        this.upStair = upStair;
    }    
    
    public void useStair(){
        
    }
}