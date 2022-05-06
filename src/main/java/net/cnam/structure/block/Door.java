package net.cnam.structure.block;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class Door extends Block {

    private boolean locked = false;

    @Override
    public boolean isTransparent() {
        return !locked;
    }

    @Override
    public String getCharacter() {
        return "D";
    }
}
