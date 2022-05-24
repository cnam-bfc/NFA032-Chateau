package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.structure.block.Block;

public abstract class DecorativeBlock extends Block {

    private final String name;

    public DecorativeBlock(String name) {
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }   
}
