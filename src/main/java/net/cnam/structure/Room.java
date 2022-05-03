package net.cnam.structure;

import net.cnam.object.Location;
import net.cnam.structure.block.Block;

/**
 * Classe d'une pièce
 */
public class Room {

    private final Location location;
    private Block[][] blocks;

    /**
     * Constructeur
     *
     * @param location Coordonnées de la pièce
     * @param blocks Tableau des blocks de la pièce
     */
    public Room(Location location, Block[][] blocks) {
        this.location = location;
        this.blocks = blocks;
    }

    /**
     * Méthode permettant de récupérer les coordonnées de la pièce
     *
     * @return les coordonnées
     */
    public Location getLocation() {
        return location;
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

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        String result ="";
        
        for (int x = 0; x < this.blocks.length; x++) {
            for (int y = 0; y < this.blocks[x].length; y++) {
                System.out.println(this.blocks[x].length);
                if (this.blocks[x][y] == null) result += " ";
                else result += this.blocks[x][y].getCharacter();
                }
            result += "\n";
            }
        return result;
    }   

}
