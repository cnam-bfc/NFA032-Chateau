package net.cnam.chateau.structure.block.decorative;

public class Seat extends DecorativeBlock {
    public Seat() {
        super("Chaise");
    }

    @Override
    public String getCharacter() {
        return "S";
    }
}
