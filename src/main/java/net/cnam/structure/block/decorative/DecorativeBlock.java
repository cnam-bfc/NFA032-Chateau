package net.cnam.structure.block.decorative;

import net.cnam.structure.block.Block;

public abstract class DecorativeBlock extends Block {
    
    Object object;

    public DecorativeBlock(Object object) {
        this.object = object;
    }
    
    public boolean hasItem(){
        if (this.object == null) return false;
        return true;
    }

    public Object getObject() {
        return object;
    }

}
