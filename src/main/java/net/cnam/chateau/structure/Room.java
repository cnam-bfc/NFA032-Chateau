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

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public int getLength() {
        return blocks.length;
    }

    public int getHeight() {
        return blocks[0].length;
    }

    public boolean isVisible() {
        return visible;
    }

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
