package net.cnam.chateau.structure;

import net.cnam.chateau.utils.Location;
import net.cnam.chateau.structure.block.Block;

/**
 * Classe d'une pièce
 */
public class Room {

    private final Location location;
    private Block[][] blocks;
    private boolean visible = false;

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
     * Setter permettant de définir le nouveau tableau de block représentant la pièce.
     * 
     * @param blocks tableau à deux dimensions des différents blocks de la pièces
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Getter permettant de récupérer la longueur de la pièce.
     * 
     * @return la longueur de la pièce (int)
     */
    public int getLength() {
        return blocks.length;
    }

    /**
     * Getter permettant de récupérer la hauteur de la pièce.
     * 
     * @return la hauteur de la pièce (int)
     */
    public int getHeight() {
        return blocks[0].length;
    }

    /**
     * Méthode pour savoir si la pièce est visible ou non.
     * 
     * @return true si visible / false si non visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Setter permettant de définir la visibilité de la pièce.
     * 
     * @param visible true visible / false non visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

//    @Override
//    public String[] render() {
//        String[] result = new String[this.getHeight()];
//
//        for (int y = 0; y < blocks[0].length; y++) {
//            String line = "";
//            for (int x = 0; x < blocks.length; x++) {
//                Block block = blocks[x][y];
//                if (block != null) {
//                    line += block.getCharacter();
//                } else {
//                    line += ' ';
//                }
//            }
//            result[y] = line;
//        }
//
//        return result;
//    }
}
