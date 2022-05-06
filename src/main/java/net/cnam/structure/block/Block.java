package net.cnam.structure.block;

import net.cnam.object.DisplayableObject;

/**
 * Classe permettant de créer un block pour la map.
 */
public abstract class Block implements DisplayableObject {

    public abstract boolean isTransparent();
}
