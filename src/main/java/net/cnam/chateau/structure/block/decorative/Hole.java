package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block trou, si tombe dans le trou, le joueur tombe à l'étage inférieur pour la map.
 */
public class Hole extends DecorativeBlock {

    public Hole() {
        this(null);
    }

    public Hole(Item hiddenItem) {
        super("Hole", hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "H";
    }
}
