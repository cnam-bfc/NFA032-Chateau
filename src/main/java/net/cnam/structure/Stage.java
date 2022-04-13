package net.cnam.structure;

import net.cnam.structure.block.Block;

public class Stage extends Structure {
    
//CHAMPS
    private int length;
    private int width;
    private Room[] room;
    private StageMap stageMap;
    
//CONSTRUCTEUR

    public Stage(int length, int width) {
        this.length = length;
        this.width = width;
    }
    

}
