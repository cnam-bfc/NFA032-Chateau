package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

/**
 * Class permettant de créer un block trou, si tombe dans le trou, le joueur tombe à l'étage inférieur pour la map.
 */
public class Hole extends DecorativeBlock {

    public Hole() {
        this(null);
    }

    public Hole(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "H";
    }
}
