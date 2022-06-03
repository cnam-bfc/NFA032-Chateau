package net.cnam.chateau.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.block.Stair;
import net.cnam.chateau.structure.block.UpStair;
import net.cnam.chateau.structure.block.container.Chest;
import net.cnam.chateau.structure.block.door.Door;
import net.cnam.chateau.structure.block.door.LockedDoor;
import net.cnam.chateau.utils.Location;

import java.util.*;

public class GUtils {
    /**
     * Méthode static qui cherche aléatoirement un endroit dans la room ou
     * mettre un block. Si une porte se trouve à proximité, déplace le block. Si
     * la position contient déjà un autre block, choisie une nouvelle position.
     *
     * @param random random de la seed
     * @param room   la room ou l'on doit placer le block
     * @return la location (x, y) par rapport à la room de l'emplacement a attribué au block
     */
    public static Location findPosition(Random random, Room room) {
        do {
            //on choisit des coordonnées au hasard dans la room choisie au-dessus
            int x = random.nextInt(1, room.getLength() - 1);
            int y = random.nextInt(1, room.getHeight() - 1);
            //vérification qu'il n'y a pas de porte à proximité sinon on décale
            //vérification qu'il n'y a pas de block là où on souhaite placer l'escalier
            if (room.getBlocks()[x][y] == null &&
                    !(room.getBlocks()[x][y - 1] instanceof Door || room.getBlocks()[x][y - 1] instanceof Stair) &&
                    !(room.getBlocks()[x][y + 1] instanceof Door || room.getBlocks()[x][y + 1] instanceof Stair) &&
                    !(room.getBlocks()[x - 1][y] instanceof Door || room.getBlocks()[x - 1][y] instanceof Stair) &&
                    !(room.getBlocks()[x + 1][y] instanceof Door || room.getBlocks()[x + 1][y] instanceof Stair)) {
                return new Location(x, y);
            }
        } while (true);
    }

    /**
     * Méthode permettant de retrouver une Room dans une liste de GRooms.
     *
     * @param room   la pièce à retrouver
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
     * Méthode qui effectue un tri topologique en partant de la Room de départ
     * de l'étage. Permet de classer les rooms en fonction de l'accessibilité.
     */
    public static UpStair triTopo(App app, Room room, List<GRoom> gRooms, Random random) {

        List<List<GRoom>> decompositionNiveau = new ArrayList<>();
        Map<GRoom, Boolean> verifyDecomposition = new HashMap<>();

        List<GRoom> transition = new ArrayList<>();
        GRoom actualGRoom = findGRoom(room, gRooms);
        transition.add(actualGRoom);
        decompositionNiveau.add(transition);

        //On prépare la liste pour le tri topologique
        //True = déjà traité (ici uniquement la room de départ)
        //False = non traité
        for (GRoom newRoom : gRooms) {
            if (newRoom == actualGRoom) {
                verifyDecomposition.put(newRoom, true);
            }
            verifyDecomposition.put(newRoom, false);
        }

        //boucle sur la liste contenant les listes représentant les différents niveaux topologiques
        for (int i = 0; i < decompositionNiveau.size(); i++) {

            if (!verifyDecomposition.containsValue(false)) {
                break;
            }

            List<GRoom> actualLevel = decompositionNiveau.get(i);
            List<GRoom> newLevel = new ArrayList<>();

            //On boucle sur toutes les GRoom du niveau actuel et on récupère pour chacune les pièces adjacentes
            for (GRoom gRoom : actualLevel) {
                List<GRoom> gRoomsAdjacente = gRoom.roomAdjacent();

                //on boucle sur tout les GWall de la GRoom, et on vérifie si les pièces accessibles sont déjà "visité"
                //si Oui, on continue
                //Si non, on passe dans le hashset, le boolean en true et on l'ajoute à la nouvelle liste
                for (GRoom c : gRoomsAdjacente) {
                    if (!verifyDecomposition.get(c)) {
                        verifyDecomposition.replace(c, true);
                        newLevel.add(c);
                    }
                }

            }

            if (!newLevel.isEmpty()) {
                decompositionNiveau.add(newLevel);
            }
        }
        return placeExit(app, decompositionNiveau, random);
    }

    private static UpStair placeExit(App app, List<List<GRoom>> decompositionNiveau, Random random) {
        //ici on choisie la room
        List<GRoom> rooms = decompositionNiveau.get(decompositionNiveau.size() - 1);
        Room room = rooms.get(random.nextInt(0, rooms.size())).getRoom();

        //on ferme la pièce et on cache la clé dans une autre pièce
        hideKey(app, room, decompositionNiveau, random);

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

    /**
     * Méthode qui permet de fermer la salle de l'escalier et caché une clé dans une autre salle.
     *
     * @param app                 l'application
     * @param room                la room de sortie de l'étage
     * @param decompositionNiveau la décomposition des gRoom en niveau par rapport à l'entrée
     * @param random              le random utilisé pour la graine de la carte
     */
    private static void hideKey(App app, Room room, List<List<GRoom>> decompositionNiveau, Random random) {
        Key key = new Key();

        // on vérifie toutes les portes et on les verrouille
        for (int x = 0; x < room.getLength(); x++) {
            for (int y = 0; y < room.getHeight(); y++) {
                if (room.getBlocks()[x][y] instanceof Door transition) {
                    room.getBlocks()[x][y] = new LockedDoor(app, transition.getStage(), transition.getRoomOne(), transition.getRoomTwo(), key);
                    try {
                        transition.getStage().setBlock(room.getLocation().getX() + x, room.getLocation().getY() + y, new LockedDoor(app, transition.getStage(), transition.getRoomOne(), transition.getRoomTwo(), key));
                    } catch (CoordinatesOutOfBoundsException ignored) {
                    }
                }
            }
        }

        // on choisit une pièce au hasard entre celle du début et celle du tri topo, différent de celle déjà sélectionnée
        Room keyRoom;
        do {
            List<GRoom> rooms = decompositionNiveau.get(random.nextInt(0, decompositionNiveau.size()));
            keyRoom = rooms.get(random.nextInt(0, rooms.size())).getRoom();
        } while (keyRoom == room);
        Location location = findPosition(random, keyRoom);
        int x = location.getX();
        int y = location.getY();
        keyRoom.getBlocks()[x][y] = new Chest(app, key); // TODO à modif pour faire varier block
    }
}
