package net.cnam.generator;

import java.util.LinkedList;
import java.util.List;
import net.cnam.object.Location;
import net.cnam.structure.Room;
import net.cnam.structure.Stage;
import net.cnam.utils.direction.Orientation;

public class GeneratorRoom {

    private final List<GeneratorWall> walls = new LinkedList<>();
    private final List<GeneratorWall> sideWalls = new LinkedList<>();

    public GeneratorRoom(Room room, Stage stage) {
        Location roomLocation = room.getLocation();

        // Génération des murs de la pièce
        // Mur de gauche
        // Si il n'est pas sur la bordure gauche de l'étage
        if (roomLocation.getX() != 0) {
            Location wallLocation = new Location(roomLocation.getX(), roomLocation.getY() + 1);
            walls.add(new GeneratorWall(wallLocation, Orientation.VERTICAL, room.getHeight() - 2, room));
        }
        // Mur du haut
        // Si il n'est pas sur la bordure haute de l'étage
        if (roomLocation.getY() != 0) {
            Location wallLocation = new Location(roomLocation.getX() + 1, roomLocation.getY());
            walls.add(new GeneratorWall(wallLocation, Orientation.HORIZONTAL, room.getLength() - 2, room));
        }
        // Mur de droite
        // Si il n'est pas sur la bordure droite de l'étage
        if (roomLocation.getX() + room.getLength() != stage.getLength()) {
            Location wallLocation = new Location(roomLocation.getX() + room.getLength() - 1, roomLocation.getY() + 1);
            walls.add(new GeneratorWall(wallLocation, Orientation.VERTICAL, room.getHeight() - 2, room));
        }
        // Mur du bas
        // Si il n'est pas sur la bordure basse de l'étage
        if (roomLocation.getY() + room.getHeight() != stage.getHeight()) {
            Location wallLocation = new Location(roomLocation.getX() + 1, roomLocation.getY() + room.getHeight() - 1);
            walls.add(new GeneratorWall(wallLocation, Orientation.HORIZONTAL, room.getLength() - 2, room));
        }
    }
    
    // TODO Remplir la pièce
    public void fillRoom(){
        
    }

    public List<GeneratorWall> getWalls() {
        return walls;
    }

    public List<GeneratorWall> getSideWalls() {
        return sideWalls;
    }
}
