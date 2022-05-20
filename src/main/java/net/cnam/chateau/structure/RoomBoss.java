package net.cnam.chateau.structure;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.DownStair;
import net.cnam.chateau.structure.block.Wall;
import net.cnam.chateau.utils.Location;

public class RoomBoss extends Room {

    private DownStair entry;

    public RoomBoss() {
        super(new Location(0, 0), null);
        Block[][] blocks = new Block[13][17];
        super.setBlocks(blocks);

// On génère les murs de Est, Ouest, et nord de 3 d'epaisseurs
        // nord 3 blocs
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < 3; y++) {
                blocks[x][y] = new Wall();
            }
        }

        // sud 3 blocs
        for (int x = 0; x < blocks.length; x++) {
            for (int y = blocks[0].length - 4 ; y < blocks[0].length - 1; y++) {
                blocks[x][y] = new Wall();
            }
        }

        // Ouest 3 blocs
        for (int x = 0; x < 3; x++) {
            for (int y = 3; y < blocks[0].length - 3; y++) {
                blocks[x][y] = new Wall();
            }
        }

        // Est 3 blocs
        for (int x = blocks.length - 3; x < blocks.length; x++) {
            for (int y = 3; y < blocks[0].length - 3; y++) {
                blocks[x][y] = new Wall();
            }
        }

        //génère 2 d'épaisseur supplémentaie pour faire un "couloir" sur le côté ouest 
        for (int x = 3; x < 5; x++) {
            for (int y = blocks[0].length - 10; y < blocks[0].length - 3; y++) {
                blocks[x][y] = new Wall();
            }
        }

        //génère 2 d'épaisseur supplémentaie pour faire un "couloir" sur le côté est
        for (int x = blocks.length - 5; x < blocks.length - 3; x++) {
            for (int y = blocks[0].length - 10; y < blocks[0].length - 3; y++) {
                blocks[x][y] = new Wall();

            }
        }

        // On place l'entrée en bas du couloir
        this.entry = new DownStair();
        blocks[6][blocks[0].length - 6] = entry;
        this.entry.setLocation(new Location(6, blocks[0].length - 6));

        // On place le boss
    }

    public DownStair getEntry() {
        return entry;
    }
}
