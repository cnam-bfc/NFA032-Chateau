package net.cnam.chateau.structure.block;

/**
 * Class permettant de créer un block Mur (Wall) pour la map.
 */
public class Wall extends Block {

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String getCharacter() {
        return "#";
    }
}
