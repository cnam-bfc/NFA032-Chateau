package net.cnam.chateau.generator;

import net.cnam.chateau.structure.block.door.Door;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static net.cnam.chateau.generator.GUtils.findPosition;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.block.*;
import net.cnam.chateau.utils.Location;

// TODO mettre dans le générateur à la fin

public class GSolver {

    private List<List<GRoom>> decompositionNiveau = new ArrayList<>();
    private Map<GRoom, Boolean> verifDecomposition = new HashMap<>();
    private Random random;
    private Room exitRoom;
    private UpStair exitStair;

    public GSolver(Room room, List<GRoom> gRooms, Random random) {
        this.random = random;

        List<GRoom> transition = new ArrayList<>();
        GRoom actualGRoom = findGRoom(room, gRooms);
        transition.add(actualGRoom);
        decompositionNiveau.add(transition);

        for (GRoom newRoom : gRooms) {
            if (newRoom == actualGRoom) {
                verifDecomposition.put(newRoom, true);
            }
            verifDecomposition.put(newRoom, false);
        }

        triTopo();
        placeExit();

    }

    /**
     * Méthode qui effectue un tri topologique en partant de la Room de départ de l'étage.
     * Permet de classer les rooms en fonction de l'accessibilité.
     * 
     */
    private void triTopo() {
        //boucle sur la liste contenant les listes représentant les différents niveau topolique
        for (int i = 0; i < decompositionNiveau.size(); i++) {

            if (!verifDecomposition.containsValue(false)) {
                return;
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
        //boucle sur les Gwall des Groom

    }

    private void placeExit() {
        //ici on choisie la room
        List<GRoom> rooms = decompositionNiveau.get(decompositionNiveau.size() - 1);
        Room room = rooms.get(random.nextInt(0, rooms.size())).getRoom();

        //On récupère un emplacement ou il est possible de placer la porte de sortie de l'étage
        Location location = findPosition(this.random, room);
        int x = location.getX();
        int y = location.getY();
        
        UpStair exitStair = new UpStair();
        room.getBlocks()[x][y] = exitStair;

        this.exitRoom = room;
        this.exitStair = exitStair;
        this.exitStair.setLocation(new Location(exitRoom.getLocation().getX() + x, exitRoom.getLocation().getY() + y));
    }

    private GRoom findGRoom(Room room, List<GRoom> GRooms) {
        for (GRoom c : GRooms) {
            if (room == c.getRoom()) {
                return c;
            }
        }
        return null;
    }

    public Room getExitRoom() {
        return exitRoom;
    }

    /**
     * Getter pour récupérer la porte de sortie de l'étage
     * 
     * @return un escalier qui monte (UpStair)
     */
    public UpStair getExitStair() {
        return exitStair;
    }
}
