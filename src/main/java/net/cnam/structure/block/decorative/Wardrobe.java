package net.cnam.structure.block.decorative;

public class Wardrobe extends DecorativeBlock {

    public Wardrobe(Object object) {
        super(object);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public String getCharacter() {
        return "W";
    }
}
