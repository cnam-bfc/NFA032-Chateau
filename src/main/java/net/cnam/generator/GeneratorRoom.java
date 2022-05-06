package net.cnam.generator;

import java.util.LinkedList;
import net.cnam.object.Location;
import net.cnam.structure.Room;
import net.cnam.utils.direction.Orientation;

public class GeneratorRoom {

    private LinkedList<GeneratorWall> ownWall = new LinkedList<>();
    private LinkedList<GeneratorWall> boundWall = new LinkedList<>();

    public GeneratorRoom(Room room) {
        Location roomOrigin = room.getLocation();
    }

    public LinkedList<GeneratorWall> getOwnWall() {
        return ownWall;
    }

    public LinkedList<GeneratorWall> getBoundWall() {
        return boundWall;
    }
}
