package net.cnam.structure;

public class Stage extends Structure {
    
//CHAMPS
    private int length;
    private int width;
    private Room[] room;
    
//CONSTRUCTEUR

    public Stage(int length, int width, Room[] room) {
        this.length = length;
        this.width = width;
        this.room = room;
    }
    

}
