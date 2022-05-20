package net.cnam.chateau.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.block.*;
import net.cnam.chateau.utils.Location;

public class GSolver {

    private List<List<GRoom>> decompositionNiveau = new ArrayList<>();
    private Map<GRoom, Boolean> verifDecomposition = new HashMap<>();
    private Random random;
    private Room exitRoom;
    private Location exitLocation;
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

        //ici on place l'escalier aléatoirement dans la room
        //vérification qu'il n'y a pas de porte à proximité sinon on décale
        int x = random.nextInt(1, room.getLength() - 1);
        int y = random.nextInt(1, room.getHeight() - 1);
        boolean testDoor;
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
        UpStair exitStair = new UpStair();
        room.getBlocks()[x][y] = exitStair;

        this.exitRoom = room;
        this.exitLocation = new Location(x, y);
        this.exitStair = exitStair;
        this.exitStair.setLocation(new Location(x, y));
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

    public Location getExitLocation() {
        return exitLocation;
    }

    public UpStair getExitStair() {
        return exitStair;
    }
}
