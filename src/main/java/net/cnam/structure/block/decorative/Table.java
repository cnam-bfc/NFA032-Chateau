package net.cnam.structure.block.decorative;

public class Table extends DecorativeBlock {

    public Table(Object object) {
        super(object);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public String getCharacter() {
        return "T";
    }
}