package net.cnam.structure.block.decorative;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends DecorativeBlock {

    public Chest(Object object) {
        super(object);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
