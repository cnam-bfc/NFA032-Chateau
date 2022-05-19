package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.CColor;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class UpStair extends Block {

    private boolean locked = false;
    private DownStair downStair;

    @Override
    public boolean isSolid() {
        return locked;
    }
    
    @Override
    public void execute() {
    }

    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + "U" + CColor.BRIGHT_YELLOW.getForegroundReset();
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public DownStair getDownStair() {
        return downStair;
    }

    public void setDownStair(DownStair downStair) {
        this.downStair = downStair;
    }
    
    public void useStair(){
        
    }
}