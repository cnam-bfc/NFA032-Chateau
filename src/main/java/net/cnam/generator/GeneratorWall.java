package net.cnam.generator;

import java.util.LinkedList;
import net.cnam.object.Location;
import net.cnam.structure.Room;
import net.cnam.utils.direction.Orientation;

public class GeneratorWall {

    private Location origin;
    private Orientation orientation;
    private int length;
    private Room room;
    private LinkedList<Location> passages = new LinkedList<>();

    public GeneratorWall(Location origin, Orientation orientation, int length, Room room) {
        this.origin = origin;
        this.orientation = orientation;
        this.length = length;
        this.room = room;
    }

    public boolean isBreakable(GeneratorWall otherWall) {
        if (this.orientation != otherWall.getOrientation()) {
            return false;
        }
        if (this.positionSegment != segmentTwo.getPositionSegment()) {
            return false;
        }
        if (segmentTwo.getStartSegment() >= this.endSegment || this.startSegment >= segmentTwo.getEndSegment()) {
            return false;
        }
        if (this.startSegment < segmentTwo.getStartSegment()) {
            if (this.length + segmentTwo.getLength() - this.startSegment + segmentTwo.getEndSegment() >= 3) {
                return true;
            } else {
                return false;
            }
        } else if (this.length + segmentTwo.getLength() - segmentTwo.getStartSegment() + this.endSegment >= 3) {
            return true;
        } else {
            return false;
        }

    }

    public Location getOrigin() {
        return origin;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getLength() {
        return length;
    }

    public Room getRoom() {
        return room;
    }
}
