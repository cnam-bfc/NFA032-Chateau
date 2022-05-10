package net.cnam.structure.block;

import net.cnam.item.DisplayableItem;

/**
 * Classe permettant de créer un block pour la map.
 */
public abstract class Block implements DisplayableItem {

    public abstract boolean isSolid();
}
