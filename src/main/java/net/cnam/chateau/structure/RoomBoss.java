package net.cnam.chateau.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.DownStair;
import net.cnam.chateau.structure.block.EndDoor;
import net.cnam.chateau.structure.block.Wall;
import net.cnam.chateau.utils.Location;

// TODO mettre dans le générateur à la fin 
public class RoomBoss extends Room {

    private final DownStair entry;
    private Block[][] blocks;


    public RoomBoss() {
        super(new Location(0, 0), null);
        this.blocks = new Block[13][17];
        super.setBlocks(this.blocks);

// On génère les murs de Est, Ouest, et nord de 3 d'epaisseurs
        // nord 3 blocs
        for (int x = 0; x < this.blocks.length; x++) {
            for (int y = 0; y < 3; y++) {
                this.blocks[x][y] = new Wall();
            }
        }

        // sud 3 blocs
        for (int x = 0; x < this.blocks.length; x++) {
            for (int y = this.blocks[0].length - 4; y < this.blocks[0].length - 1; y++) {
                this.blocks[x][y] = new Wall();
            }
        }

        // Ouest 3 blocs
        for (int x = 0; x < 3; x++) {
            for (int y = 3; y < this.blocks[0].length - 3; y++) {
                this.blocks[x][y] = new Wall();
            }
        }

        // Est 3 blocs
        for (int x = this.blocks.length - 3; x < this.blocks.length; x++) {
            for (int y = 3; y < this.blocks[0].length - 3; y++) {
                this.blocks[x][y] = new Wall();
            }
        }

        //génère 2 d'épaisseur supplémentaie pour faire un "couloir" sur le côté ouest 
        for (int x = 3; x < 5; x++) {
            for (int y = this.blocks[0].length - 10; y < this.blocks[0].length - 3; y++) {
                this.blocks[x][y] = new Wall();
            }
        }

        //génère 2 d'épaisseur supplémentaie pour faire un "couloir" sur le côté est
        for (int x = this.blocks.length - 5; x < this.blocks.length - 3; x++) {
            for (int y = this.blocks[0].length - 10; y < this.blocks[0].length - 3; y++) {
                this.blocks[x][y] = new Wall();

            }
        }

        // On place l'entrée en bas du couloir
        this.entry = new DownStair();
        this.blocks[6][this.blocks[0].length - 6] = entry;
        this.entry.setLocation(new Location(6, this.blocks[0].length - 6));

        // On place le boss
    }

    /**
     * Getter permettant de récupérer l'entrée de la pièce du boss.
     * 
     * @return L'escalier de l'entrée (DownStair)
     */
    public DownStair getEntry() {
        return entry;
    }

    /**
     * Méthode permettant d'ouvrir un passage pour la porte de fin.
     *
     * @param app l'application
     */
    public void openBossRoom(App app){
        this.blocks[5][2] = null;
        this.blocks[5][1] = null;
        this.blocks[5][0] = new EndDoor(app);
        this.blocks[6][2] = null;
        this.blocks[6][1] = null;
        this.blocks[6][0] = new EndDoor(app);
        this.blocks[7][2] = null;
        this.blocks[7][1] = null;
        this.blocks[7][0] = new EndDoor(app);
    }
}
