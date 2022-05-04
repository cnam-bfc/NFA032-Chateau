package net.cnam.structure;

import net.cnam.gui.component.CComponent;
import net.cnam.object.Location;
import net.cnam.structure.block.Block;

/**
 * Classe d'un étage
 */
public class Stage extends CComponent {

    private Room[] rooms;

    /**
     * Constructeur
     *
     * @param rooms Tableau des pièces de l'étage
     * @param length La longueur de l'étage
     * @param width La largeur de l'étage
     */
    public Stage(Room[] rooms, int length, int width) {
        super(length * 2 - 1, width);

        this.rooms = rooms;
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
        if (x > this.getLength() / 2 + 1) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + (this.getLength() / 2 + 1) + " (" + x + ")");
        }
        if (y > this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        for (Room room : rooms) {
            Location roomLocation = room.getLocation();
            // Si le block est dans la pièce
            if (x >= roomLocation.getX() && x < roomLocation.getX() + room.getLength()
                    && y >= roomLocation.getY() && y < roomLocation.getY() + room.getHeight()) {
                // On récupère le bloc en calculant ses coordonnées relatives par rapport à la pièce
                return room.getBlocks()[x - roomLocation.getX()][y - roomLocation.getY()];
            }
        }

        return null;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];

        for (int y = 0; y < this.getHeight(); y++) {
            String line = "";
            for (int x = 0; x < this.getLength() / 2 + 1; x++) {
                try {
                    Block block = getBlock(x, y);
                    line += ' ';
                    if (block != null) {
                        line += block.getCharacter();
                    } else {
                        line += ' ';
                    }
                } catch (CoordinatesOutOfBoundsException ex) {
                }
            }
            result[y] = line.replaceFirst(" ", "");
        }

        return result;
    }
}
