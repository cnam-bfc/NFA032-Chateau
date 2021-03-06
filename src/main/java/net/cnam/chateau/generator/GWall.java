package net.cnam.chateau.generator;

import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.direction.Orientation;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GWall {

    private final GRoom roomOne;
    private final GRoom roomTwo;
    private final Location location;
    private final Orientation orientation;
    private final int length;
    private final List<Location> wallsLocations;

    public GWall(GRoom roomOne, GRoom roomTwo, Location location, Orientation orientation, int length) {
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
        this.location = location;
        this.orientation = orientation;
        this.length = length;
        this.wallsLocations = new LinkedList<>();
        // On fait la liste des positions des murs
        switch (orientation) {
            case HORIZONTAL -> {
                for (int i = location.getX(); i < location.getX() + length; i++) {
                    wallsLocations.add(new Location(i, this.location.getY()));
                }
            }
            case VERTICAL -> {
                for (int i = location.getY(); i < location.getY() + length; i++) {
                    wallsLocations.add(new Location(this.location.getX(), i));
                }
            }
        }
    }

    public boolean isBroken() {
        return wallsLocations.size() != length;
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

    public List<Location> getWallsLocations() {
        return wallsLocations;
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
