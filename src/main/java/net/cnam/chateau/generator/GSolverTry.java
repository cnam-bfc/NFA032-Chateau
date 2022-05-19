package net.cnam.chateau.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.*;

public class GSolverTry {
    
    private ArrayList<ArrayList<GRoom>> decompositionNiveau = new ArrayList<>();
    private HashMap<GRoom, Boolean> verifDecomposition = new HashMap<>();
    private Random random;
    
    public GSolverTry(Room room, List<GRoom> gRooms, Random random) {
        this.random = random;
        
        ArrayList<GRoom> transition = new ArrayList<>();
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
            
            ArrayList<GRoom> actualLevel = decompositionNiveau.get(i);
            ArrayList<GRoom> newLevel = new ArrayList<>();
            
            //On boucle sur toutes les GRoom du niveau actuelle et on récupère pour chacune les pièces adjacentes
            for (int j = 0; j < actualLevel.size(); j++) {
                ArrayList<GRoom> gRoomsAdja = actualLevel.get(j).roomAdjacent();
                
                //on boucle sur tout les Gwall de la Groom, et on vérifie si les pièces accessible sont déjà "visité"
                //si Oui, on continue
                //Si non, on passe dans le hashset, le boolean en true et on l'ajoute à la nouvelle liste
                for (GRoom c : gRoomsAdja){
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
        ArrayList<GRoom> rooms = decompositionNiveau.get(decompositionNiveau.size()-1);
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
            room.getBlocks()[x][y] = new UpStair();
        
    }
    
    private GRoom findGRoom(Room room, List<GRoom> GRooms) {
        for (GRoom c : GRooms) {
            if (room == c.getRoom()) {
                return c;
            }
        }
        return null;
    }

    /*
    Commence dans une pièce
    Choisie de passer dans une pièce à côté (accessible) au hasard, et ainsi de suite tant que c'est possible
    Si ne peut plus aller dans une nouvelle pièce reviens en arrière
    répèter jusqu'à avoir explorer tout le labyrinth 
    noter la distance par rapport à la pièce initial et en choisir une suffisament loin pour en faire la sortie
    possibiliter de bloquer l'accès à la pièce et cacher un objet clé dans une des autres pièces
     */
}
