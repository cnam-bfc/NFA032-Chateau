package net.cnam.chateau.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.direction.Orientation;

public class GRoom {

    private final List<GRoomWall> walls = new LinkedList<>();
    private final List<GWall> gWalls = new ArrayList<>();
    private final Room room;

    private int mazeNb;

    public GRoom(Room room, Stage stage, int mazeNb) {
        this.room = room;
        this.mazeNb = mazeNb;

        Location roomLocation = room.getLocation();

        // Génération des murs de la pièce
        // Mur de gauche
        // S'il n'est pas sur la bordure gauche de l'étage
        if (roomLocation.getX() != 0) {
            Location wallLocation = new Location(roomLocation.getX(), roomLocation.getY() + 1);
            walls.add(new GRoomWall(wallLocation, Orientation.VERTICAL, room.getHeight() - 2, this));
        }
        // Mur du haut
        // S'il n'est pas sur la bordure haute de l'étage
        if (roomLocation.getY() != 0) {
            Location wallLocation = new Location(roomLocation.getX() + 1, roomLocation.getY());
            walls.add(new GRoomWall(wallLocation, Orientation.HORIZONTAL, room.getLength() - 2, this));
        }
        // Mur de droite
        // S'il n'est pas sur la bordure droite de l'étage
        if (roomLocation.getX() + room.getLength() != stage.getLength()) {
            Location wallLocation = new Location(roomLocation.getX() + room.getLength() - 1, roomLocation.getY() + 1);
            walls.add(new GRoomWall(wallLocation, Orientation.VERTICAL, room.getHeight() - 2, this));
        }
        // Mur du bas
        // S'il n'est pas sur la bordure basse de l'étage
        if (roomLocation.getY() + room.getHeight() != stage.getHeight()) {
            Location wallLocation = new Location(roomLocation.getX() + 1, roomLocation.getY() + room.getHeight() - 1);
            walls.add(new GRoomWall(wallLocation, Orientation.HORIZONTAL, room.getLength() - 2, this));
        }
    }

    public List<GRoomWall> getWalls() {
        return walls;
    }

    public int getMazeNb() {
        return mazeNb;
    }

    public void setMazeNb(int mazeNb) {
        this.mazeNb = mazeNb;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.walls);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GRoom other = (GRoom) obj;
        return Objects.equals(this.walls, other.walls);
    }

    public Room getRoom() {
        return room;
    }

    public List<GWall> getGWalls() {
        return gWalls;
    }
    
    public ArrayList<GRoom> roomAdjacent(){
        ArrayList<GRoom> result = new ArrayList<>();
        for(GWall c : this.gWalls ){ 
            if (c.isBreaked()){
                if (c.getRoomOne() != this) result.add(c.getRoomOne());
                if (c.getRoomTwo() != this) result.add(c.getRoomTwo());
            }
        }
        return result;
    }
}
