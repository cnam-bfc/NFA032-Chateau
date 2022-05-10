package net.cnam.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.cnam.utils.Location;
import net.cnam.structure.Room;
import net.cnam.utils.direction.Orientation;

public class GeneratorWall {

    private final Location location;
    private final Orientation orientation;
    private final int length;
    private final Room room;
    private final List<Location> passages = new LinkedList<>();

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
                // Minimum
                int min = this.location.getX();
                int minWall = wall.location.getX();
                if (minWall > min) {
                    min = minWall;
                }
                // Maximum
                int max = this.location.getX() + this.length - 1;
                int maxWall = wall.location.getX() + wall.length - 1;
                if (max > maxWall) {
                    max = maxWall;
                }
                // Positions possibles
                for (int i = min; i <= max; i++) {
                    possibleBreakPoints.add(new Location(i, this.location.getY()));
                }
            }
            case VERTICAL -> {
                // Minimum
                int min = this.location.getY();
                int minWall = wall.location.getY();
                if (minWall > min) {
                    min = minWall;
                }
                // Maximum
                int max = this.location.getY() + this.length - 1;
                int maxWall = wall.location.getY() + wall.length - 1;
                if (max > maxWall) {
                    max = maxWall;
                }
                // Positions possibles
                for (int i = min; i <= max; i++) {
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

    public List<Location> getPassages() {
        return passages;
    }
}
