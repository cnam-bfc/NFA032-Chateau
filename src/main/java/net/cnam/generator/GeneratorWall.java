package net.cnam.generator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.cnam.object.Location;
import net.cnam.structure.CoordinatesOutOfBoundsException;
import net.cnam.structure.Room;
import net.cnam.structure.Stage;
import net.cnam.utils.direction.Orientation;

public class GeneratorWall {

    private final Location location;
    private final Orientation orientation;
    private final int length;
    private final Room room;
    private final Set<Location> passages = new HashSet<>();

    public GeneratorWall(Location location, Orientation orientation, int length, Room room) {
        this.location = location;
        this.orientation = orientation;
        this.length = length;
        this.room = room;
    }

    public boolean overlapWall(GeneratorWall wall) {
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
                if (this.location.getX() > wall.location.getX() + wall.length || this.location.getX() + this.length < wall.location.getX()) {
                    return false;
                }
            }
            case VERTICAL -> {
                // Si ils ne sont pas au même niveau (sur la même ligne ou sur la même colonne suivant l'orientation), ils ne se chevauchent pas
                if (this.location.getX() != wall.location.getX()) {
                    return false;
                }
                // Si ils ne se chevauchent pas
                if (this.location.getY() > wall.location.getY() + wall.length || this.location.getY() + this.length < wall.location.getY()) {
                    return false;
                }
            }
        }

        // Ils se chevauchent !
        return true;
    }

    public void breakWall(GeneratorWall wall, Random rnd) {
        if (!overlapWall(wall)) {
            return;
        }
        // On fait la liste des points en communs avec les 2 murs
        List<Location> possibleBreakPoints = new LinkedList<>();
        switch (this.orientation) {
            case HORIZONTAL -> {
                int min = this.location.getX();
                if (wall.location.getX() > min) {
                    min = wall.location.getX();
                }
                int max = this.location.getX() + this.length;
                if (max > wall.location.getX() + wall.length) {
                    max = wall.location.getX() + wall.length;
                }
                for (int i = min; i < max; i++) {
                    possibleBreakPoints.add(new Location(i, this.location.getY()));
                }
            }
            case VERTICAL -> {
                int min = this.location.getY();
                if (wall.location.getY() > min) {
                    min = wall.location.getY();
                }
                int max = this.location.getY() + this.length;
                if (max > wall.location.getY() + wall.length) {
                    max = wall.location.getY() + wall.length;
                }
                for (int i = min; i < max; i++) {
                    possibleBreakPoints.add(new Location(this.location.getX(), i));
                }
            }
        }

        // On enlève ceux qui sont déjà des passages
        possibleBreakPoints.removeAll(passages);

        // Si il n'y a pas de positions en commun, on peu pas créer de passage donc on abandonne
        if (possibleBreakPoints.isEmpty()) {
            return;
        }

        Location breakPoint = possibleBreakPoints.get(rnd.nextInt(possibleBreakPoints.size()));
        passages.add(breakPoint);
        wall.passages.add(breakPoint);
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

    public Room getRoom() {
        return room;
    }

    public Set<Location> getPassages() {
        return passages;
    }
}
