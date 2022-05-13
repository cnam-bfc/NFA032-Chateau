package net.cnam.chateau.structure.block;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class Door extends Block {

    private boolean locked = false;

    @Override
    public boolean isSolid() {
        return locked;
    }

    @Override
    public String getCharacter() {
        return "D";
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
