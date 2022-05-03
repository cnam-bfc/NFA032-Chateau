package net.cnam.structure;

import net.cnam.gui.component.CComponent;
import net.cnam.object.Location;
import net.cnam.structure.block.Block;

/**
 * Classe d'une pièce
 */
public class Room extends CComponent {

    private final Location location;
    private Block[][] blocks;

    /**
     * Constructeur
     *
     * @param location Coordonnées de la pièce
     * @param blocks Tableau des blocks de la pièce
     */
    public Room(Location location, Block[][] blocks) {
        super(blocks.length, blocks[0].length);

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
        this.setSize(blocks.length, blocks[0].length);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];

        for (int y = 0; y < blocks[0].length; y++) {
            String line = "";
            for (int x = 0; x < blocks.length; x++) {
                Block block = blocks[x][y];
                if (block != null) {
                    line += block.getCharacter();
                } else {
                    line += ' ';
                }
            }
            result[y] = line;
        }

        return result;
    }
}
