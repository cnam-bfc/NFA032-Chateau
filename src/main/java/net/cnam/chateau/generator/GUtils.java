package net.cnam.chateau.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.UpStair;
import net.cnam.chateau.structure.block.decorative.Bed;
import net.cnam.chateau.structure.block.decorative.Chest;
import net.cnam.chateau.structure.block.decorative.DecorativeBlock;
import net.cnam.chateau.structure.block.decorative.Table;
import net.cnam.chateau.structure.block.decorative.Wardrobe;
import net.cnam.chateau.structure.block.door.Door;
import net.cnam.chateau.utils.Location;

public class GUtils {

    private static final int LUCK_BLOCK = 70; // chance d'avoir un blocks rare dans la pièce

    /**
     * Méthode static qui cherche aléatoirement un endroit dans la room ou
     * mettre un block. Si une porte se trouve à proximité, déplace le block. Si
     * la position contient déjà un autre block, choisie une nouvelle position.
     *
     * @param random random de la seed
     * @param room la room ou l'on doit placé le block
     * @return la location (x,y) par rapport à la room de l'emplacement à
     * attribué au block
     */
    public static Location findPosition(Random random, Room room) {
        //on choisit des coordonnées au hasard dans la room choisie au dessus
        int x = random.nextInt(1, room.getLength() - 1);
        int y = random.nextInt(1, room.getHeight() - 1);
        boolean testDoor;
        //vérification qu'il n'y a pas de porte à proximité sinon on décale
        //vérification qu'il n'y a pas de block la ou on souhaite placé l'escalier
        do {
            testDoor = false;
            if (room.getBlocks()[x + 1][y] instanceof Door || room.getBlocks()[x + 1][y] instanceof DecorativeBlock) {
                x -= 1;
                testDoor = true;
            }
            if (room.getBlocks()[x - 1][y] instanceof Door || room.getBlocks()[x - 1][y] instanceof DecorativeBlock) {
                x += 1;
                testDoor = true;
            }
            if (room.getBlocks()[x][y + 1] instanceof Door || room.getBlocks()[x][y + 1] instanceof DecorativeBlock) {
                y -= 1;
                testDoor = true;
            }
            if (room.getBlocks()[x][y - 1] instanceof Door || room.getBlocks()[x][y - 1] instanceof DecorativeBlock) {
                y += 1;
                testDoor = true;
            }
            if (room.getBlocks()[x][y] != null) {
                x = random.nextInt(1, room.getLength() - 1);
                y = random.nextInt(1, room.getHeight() - 1);
                testDoor = true;
            }
        } while (testDoor);
        return new Location(x, y);
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

    // TODO voir si on la laisse ici ou dans le Solver
    /**
     * Méthode permettant de retrouver une Room dans une liste de GRooms.
     *
     * @param room la pièce à retrouver
     * @param GRooms la liste de pièce à vérifier
     * @return la GRoom associée à la pièce
     */
    public static GRoom findGRoom(Room room, List<GRoom> GRooms) {
        for (GRoom c : GRooms) {
            if (room == c.getRoom()) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Méthode qui effectue un tri topologique en partant de la Room de départ de l'étage.
     * Permet de classer les rooms en fonction de l'accessibilité.
     * 
     */
    public static UpStair triTopo(Room room, List<GRoom> gRooms, Random random) {
        
        List<List<GRoom>> decompositionNiveau = new ArrayList<>();
        Map<GRoom, Boolean> verifDecomposition = new HashMap<>();
        
        List<GRoom> transition = new ArrayList<>();
        GRoom actualGRoom = findGRoom(room, gRooms);
        transition.add(actualGRoom);
        decompositionNiveau.add(transition);

        //On prépare la liste pour le tri topoligue
        //True = déjà traité (ici uniquement la room de départ)
        //False = non traité
        for (GRoom newRoom : gRooms) {
            if (newRoom == actualGRoom) {
                verifDecomposition.put(newRoom, true);
            }
            verifDecomposition.put(newRoom, false);
        }
        
        //boucle sur la liste contenant les listes représentant les différents niveau topolique
        for (int i = 0; i < decompositionNiveau.size(); i++) {

            if (!verifDecomposition.containsValue(false)) {
                break;
            }

            List<GRoom> actualLevel = decompositionNiveau.get(i);
            List<GRoom> newLevel = new ArrayList<>();

            //On boucle sur toutes les GRoom du niveau actuelle et on récupère pour chacune les pièces adjacentes
            for (int j = 0; j < actualLevel.size(); j++) {
                List<GRoom> gRoomsAdja = actualLevel.get(j).roomAdjacent();

                //on boucle sur tout les Gwall de la Groom, et on vérifie si les pièces accessible sont déjà "visité"
                //si Oui, on continue
                //Si non, on passe dans le hashset, le boolean en true et on l'ajoute à la nouvelle liste
                for (GRoom c : gRoomsAdja) {
                    if (!verifDecomposition.get(c)) {
                        verifDecomposition.replace(c, true);
                        newLevel.add(c);
                    }
                }

            }

            if (!newLevel.isEmpty()) {
                decompositionNiveau.add(newLevel);
            }
        }
        return placeExit(decompositionNiveau, random);

    }
    
    private static UpStair placeExit(List<List<GRoom>> decompositionNiveau, Random random) {
        //ici on choisie la room
        List<GRoom> rooms = decompositionNiveau.get(decompositionNiveau.size() - 1);
        Room room = rooms.get(random.nextInt(0, rooms.size())).getRoom();

        //On récupère un emplacement ou il est possible de placer la porte de sortie de l'étage
        Location location = findPosition(random, room);
        int x = location.getX();
        int y = location.getY();
        
        // On place l'escalier de sortie
        UpStair exitStair = new UpStair();
        room.getBlocks()[x][y] = exitStair;
        exitStair.setLocation(new Location(room.getLocation().getX() + x, room.getLocation().getY() + y));
        return exitStair;
    }

}
