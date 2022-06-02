package net.cnam.chateau.structure.block;

import net.cnam.chateau.gui.DisplayableObject;

/**
 * Classe permettant de créer un block pour la map.
 */
public abstract class Block implements DisplayableObject {

    private String name;

    /**
     * Constructeur de la classe.
     *
     * @param name Le nom du block.
     */
    public Block(String name) {
        this.name = name;
    }

    /**
     * Retourne le nom du block.
     *
     * @return Le nom du block.
     */
    public String getName() {
        return name;
    }

}
