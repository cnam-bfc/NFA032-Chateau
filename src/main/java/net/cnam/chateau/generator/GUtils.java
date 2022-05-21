package net.cnam.chateau.generator;

import java.util.Random;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.decorative.Bed;
import net.cnam.chateau.structure.block.decorative.Chest;
import net.cnam.chateau.structure.block.decorative.Table;
import net.cnam.chateau.structure.block.decorative.Wardrobe;
import net.cnam.chateau.structure.block.door.Door;
import net.cnam.chateau.utils.Location;

public class GUtils {
    
    private static final int LUCK_BLOCK = 70; // chance d'avoir un blocks rare dans la pièce
    
    /**
     * Méthode static qui cherche aléatoirement un endroit dans la room ou mettre un block.
     * Si une porte se trouve à proximité, déplace le block.
     * Si la position contient déjà un autre block, choisie une nouvelle position.
     * 
     * @param random random de la seed
     * @param room la room ou l'on doit placé le block
     * @return la location (x,y) par rapport à la room de l'emplacement à attribué au block
     */
    public static Location findPosition(Random random, Room room){
        //on choisit des coordonnées au hasard dans la room choisie au dessus
                int x = random.nextInt(1, room.getLength() - 1);
                int y = random.nextInt(1, room.getHeight() - 1);
                boolean testDoor;
                //vérification qu'il n'y a pas de porte à proximité sinon on décale
                //vérification qu'il n'y a pas de block la ou on souhaite placé l'escalier
                do {
                    testDoor = false;
                    if (room.getBlocks()[x + 1][y] instanceof Door) {
                        x -= 1;
                        testDoor = true;
                    }
                    if (room.getBlocks()[x - 1][y] instanceof Door) {
                        x += 1;
                        testDoor = true;
                    }
                    if (room.getBlocks()[x][y + 1] instanceof Door) {
                        y -= 1;
                        testDoor = true;
                    }
                    if (room.getBlocks()[x + 1][y - 1] instanceof Door) {
                        y += 1;
                        testDoor = true;
                    }
                    if (room.getBlocks()[x][y] != null) {
                        x = random.nextInt(1, room.getLength() - 1);
                        y = random.nextInt(1, room.getHeight() - 1);
                        testDoor = true;
                    }
                } while (testDoor);
                return new Location(x,y);
    }
    
    /**
     * Méthode pour choisir le bloc à placer dans la pièce.
     *
     * @return renvoie un block aléatoire
     */
    public static Block pickRandomBlock(Random random) {

        if (random.nextInt(1, 101) > LUCK_BLOCK) {
            switch (random.nextInt(1, 3)) {
                case 1 -> {
                    return new Chest();
                }
                case 2 -> {
                    return new Bed();
                }
            }
        } else {
            switch (random.nextInt(1, 3)) {
                case 1 -> {
                    return new Wardrobe();
                }
                case 2 -> {
                    return new Table();
                }
            }
        }
        return null;
    }
    
}
