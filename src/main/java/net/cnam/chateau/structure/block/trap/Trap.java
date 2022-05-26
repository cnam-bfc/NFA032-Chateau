package net.cnam.chateau.structure.block.trap;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.structure.block.Block;

abstract public class Trap extends Block {

    private String name;
    private String description ="";
    private boolean activate = true;

    public Trap(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActivate(Boolean bool) {
        this.activate = bool;
    }

    public boolean isUsed(){
        return !activate;
    }

    abstract public void useEffect(Player player);

    @Override
    public String getCharacter() {
        return CColor.RED +  "T" + CColor.RED.getForegroundReset();
    }
}
