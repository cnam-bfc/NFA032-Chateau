package net.cnam.structure.block;

/**
 * Class permettant de créer un block Mur (Wall) pour la map.
 */
public class Wall extends Block {

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public String getCharacter() {
        return "#";
    }
}
