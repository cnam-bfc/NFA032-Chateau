package net.cnam.structure;

import net.cnam.object.Location;
import net.cnam.structure.block.Block;

/**
 * Classe d'un étage
 */
public class Stage {

    private Room[] rooms;
    private final int length;
    private final int width;

    /**
     * Constructeur
     *
     * @param rooms Tableau des pièces de l'étage
     * @param length La longueur de l'étage
     * @param width La largeur de l'étage
     */
    public Stage(Room[] rooms, int length, int width) {
        this.rooms = rooms;
        this.length = length;
        this.width = width;
    }

    /**
     * Méthode permettant de récupérer les pièces de l'étage
     *
     * @return Les pièces
     */
    public Room[] getRooms() {
        return rooms;
    }

    /**
     * Méthode permettant de récupérer la longueur de l'étage
     *
     * @return Longueur de l'étage
     */
    public int getLength() {
        return length;
    }

    /**
     * Méthode permettant de récupérer la largeur de l'étage
     *
     * @return Largeur de l'étage
     */
    public int getWidth() {
        return width;
    }

    /**
     * Méthode pour récupérer le block aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return Le bloc aux coordonnées spécifiés
     * @throws net.cnam.structure.CoordinatesOutOfBoundsException Exception
     * lorsque les coordonnées ne sont pas contenu dans la taille de l'étage
     */
    public Block getBlock(int x, int y) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x > this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y > this.getWidth()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getWidth() + " (" + y + ")");
        }

        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            Location roomLocation = room.getLocation();
            // Si le block est dans la pièce
            if (x > roomLocation.getX() && x < roomLocation.getX() + room.getLength()
                    && y > roomLocation.getY() && y < roomLocation.getY() + room.getWidth()) {
                // On récupère le bloc en calculant ses coordonnées relatives par rapport à la pièce
                return room.getBlocks()[x - roomLocation.getX()][y - roomLocation.getY()];
            }
        }

        return null;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
    
}
