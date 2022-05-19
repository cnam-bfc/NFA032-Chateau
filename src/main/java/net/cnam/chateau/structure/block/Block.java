package net.cnam.chateau.structure.block;

import net.cnam.chateau.item.DisplayableItem;

/**
 * Classe permettant de créer un block pour la map.
 */
public abstract class Block implements DisplayableItem {

    public abstract boolean isSolid();
    
    public abstract void execute();
}
