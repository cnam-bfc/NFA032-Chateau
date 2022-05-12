package net.cnam.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import net.cnam.utils.Location;
import net.cnam.utils.direction.Orientation;

public class GWall {

    private final GRoom roomOne;
    private final GRoom roomTwo;
    private final Location location;
    private final Orientation orientation;
    private final int length;

    public GWall(GRoom roomOne, GRoom roomTwo, Location location, Orientation orientation, int length) {
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
        this.location = location;
        this.orientation = orientation;
        this.length = length;
    }

    public Location getBreakPoint(Random rnd) {
        // On fait la liste des points en communs avec les 2 murs
        List<Location> possibleBreakPoints = new LinkedList<>();
        switch (this.orientation) {
            case HORIZONTAL -> {
                // Positions possibles
                for (int i = location.getX(); i < location.getX() + length; i++) {
                    possibleBreakPoints.add(new Location(i, this.location.getY()));
                }
            }
            case VERTICAL -> {
                // Positions possibles
                for (int i = location.getY(); i < location.getY() + length; i++) {
                    possibleBreakPoints.add(new Location(this.location.getX(), i));
                }
            }
        }

        Location breakPoint = possibleBreakPoints.get(rnd.nextInt(possibleBreakPoints.size()));

        return breakPoint;
    }

    public GRoom getRoomOne() {
        return roomOne;
    }

    public GRoom getRoomTwo() {
        return roomTwo;
    }

    public Location getLocation() {
        return location;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getLength() {
        return length;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.location);
        hash = 71 * hash + Objects.hashCode(this.orientation);
        hash = 71 * hash + this.length;
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
        final GWall other = (GWall) obj;
        if (this.length != other.length) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return this.orientation == other.orientation;
    }
}
