package net.cnam.structure;

import net.cnam.structure.block.Block;

/**
 * Classe d'une pièce
 */
public class Room {

    private final Block[][] blocks;

    /**
     * Constructeur
     *
     * @param blocks Tableau des blocks de la pièce
     */
    public Room(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Méthode permettant de récupérer les blocks de la pièce
     *
     * @return
     */
    public Block[][] getBlocks() {
        return blocks;
    }

    /**
     * Méthode permettant de récupérer la longueur de la pièce
     *
     * @return Longueur de la pièce
     */
    public int getLength() {
        return blocks.length;
    }

    /**
     * Méthode permettant de récupérer la largeur de la pièce
     *
     * @return Largeur de la pièce
     */
    public int getWidth() {
        return blocks[0].length;
    }
}
