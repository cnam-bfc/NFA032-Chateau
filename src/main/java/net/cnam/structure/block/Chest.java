package net.cnam.structure.block;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends Block {

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
