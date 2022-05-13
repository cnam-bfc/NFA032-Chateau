package net.cnam.chateau.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.direction.Orientation;

public class GRoomWall {

    private final Location location;
    private final Orientation orientation;
    private final int length;
    private final GRoom gRoom;

    private final List<GRoomWall> sideWalls = new LinkedList<>();

    public GRoomWall(Location location, Orientation orientation, int length, GRoom gRoom) {
        this.location = location;
        this.orientation = orientation;
        this.length = length;
        this.gRoom = gRoom;
    }

    public boolean overlapWall(GRoomWall wall) {
        // Si ils n'ont pas la même orientation, ils ne se chevauchent pas
        if (this.orientation != wall.orientation) {
            return false;
        }
        switch (this.orientation) {
            case HORIZONTAL -> {
                // Si ils ne sont pas au même niveau (sur la même ligne ou sur la même colonne suivant l'orientation), ils ne se chevauchent pas
                if (this.location.getY() != wall.location.getY()) {
                    return false;
                }
                // Si ils ne se chevauchent pas
                if (this.location.getX() >= wall.location.getX() + wall.length || this.location.getX() + this.length <= wall.location.getX()) {
                    return false;
                }
            }
            case VERTICAL -> {
                // Si ils ne sont pas au même niveau (sur la même ligne ou sur la même colonne suivant l'orientation), ils ne se chevauchent pas
                if (this.location.getX() != wall.location.getX()) {
                    return false;
                }
                // Si ils ne se chevauchent pas
                if (this.location.getY() >= wall.location.getY() + wall.length || this.location.getY() + this.length <= wall.location.getY()) {
                    return false;
                }
            }
        }

        // Ils se chevauchent !
        return true;
    }

    public GWall createWall(GRoomWall wall, Random rnd) {
        if (!overlapWall(wall)) {
            return null;
        }
        switch (this.orientation) {
            case HORIZONTAL -> {
                // Minimum
                int min = this.location.getX();
                int minWall = wall.location.getX();
                if (minWall > min) {
                    min = minWall;
                }
                // Maximum
                int max = this.location.getX() + this.length;
                int maxWall = wall.location.getX() + wall.length;
                if (maxWall < max) {
                    max = maxWall;
                }
                return new GWall(this.getGRoom(), wall.getGRoom(), new Location(min, this.location.getY()), Orientation.HORIZONTAL, max - min);
            }
            case VERTICAL -> {
                // Minimum
                int min = this.location.getY();
                int minWall = wall.location.getY();
                if (minWall > min) {
                    min = minWall;
                }
                // Maximum
                int max = this.location.getY() + this.length;
                int maxWall = wall.location.getY() + wall.length;
                if (maxWall < max) {
                    max = maxWall;
                }
                return new GWall(this.getGRoom(), wall.getGRoom(), new Location(this.location.getX(), min), Orientation.VERTICAL, max - min);
            }
        }
        return null;
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

    public GRoom getGRoom() {
        return gRoom;
    }

    public List<GRoomWall> getSideWalls() {
        return sideWalls;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.location);
        hash = 11 * hash + Objects.hashCode(this.orientation);
        hash = 11 * hash + this.length;
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
        final GRoomWall other = (GRoomWall) obj;
        if (this.length != other.length) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return this.orientation == other.orientation;
    }
}
