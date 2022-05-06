package net.cnam.structure;

import java.util.LinkedList;
import net.cnam.entity.LivingEntity;
import net.cnam.gui.component.CComponent;
import net.cnam.object.Location;
import net.cnam.structure.block.Block;

/**
 * Classe d'un étage
 */
public class Stage extends CComponent {

    private Room[] rooms;
    private LinkedList<LivingEntity> entities = new LinkedList<>();

    /**
     * Constructeur
     *
     * @param rooms Tableau des pièces de l'étage
     * @param length La longueur de l'étage
     * @param width La largeur de l'étage
     */
    public Stage(Room[] rooms, int length, int width) {
        super(length, width);

        this.rooms = rooms;
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

    /**
     * Méthode pour définir un block aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @param block Le bloc aux coordonnées spécifiés
     * @throws net.cnam.structure.CoordinatesOutOfBoundsException Exception
     * lorsque les coordonnées ne sont pas contenu dans la taille de l'étage
     */
    public void setBlock(int x, int y, Block block) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x > this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y > this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        for (Room room : rooms) {
            Location roomLocation = room.getLocation();
            // Si les coordonnées sont dans la pièce
            if (x >= roomLocation.getX() && x < roomLocation.getX() + room.getLength()
                    && y >= roomLocation.getY() && y < roomLocation.getY() + room.getHeight()) {
                // On défini le bloc en calculant ses coordonnées relatives par rapport à la pièce
                room.getBlocks()[x - roomLocation.getX()][y - roomLocation.getY()] = block;
            }
        }
    }

    /**
     * Méthode pour récupérer l'entité aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return L'entité aux coordonnées spécifiés
     * @throws net.cnam.structure.CoordinatesOutOfBoundsException Exception
     * lorsque les coordonnées ne sont pas contenu dans la taille de l'étage
     */
    public LivingEntity getEntity(int x, int y) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x > this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y > this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        for (LivingEntity entity : entities) {
            Location entityLocation = entity.getLocation();
            if (entityLocation.getX() == x && entityLocation.getY() == y) {
                return entity;
            }
        }

        return null;
    }

    /**
     * Méthode permettant de faire bouger une entité dans l'étage
     *
     * @param entity L'entité à faire bouger
     * @param relX Déplacement relatif à faire au niveau de l'abscisse
     * @param relY Déplacement relatif à faire au niveau de l'ordonnée
     * @throws net.cnam.structure.CoordinatesOutOfBoundsException Exception levé
     * si l'entitée tente de sortir de l'étage
     */
    public void move(LivingEntity entity, int relX, int relY) throws CoordinatesOutOfBoundsException {
        for (LivingEntity entity2 : entities) {
            if (entity.equals(entity2)) {
                Location entityLocation = entity.getLocation();
                int newX = entityLocation.getX() + relX;
                int newY = entityLocation.getY() + relY;
                if (newX < 0 || newY < 0 || newX >= this.getLength() || newY >= this.getHeight()) {
                    throw new CoordinatesOutOfBoundsException("L'entité ne peut pas sortir de l'étage!");
                }
                Block block = this.getBlock(newX, newY);
                if (block != null) {
                    return;
                }
                // TODO Vérifier qu'il va pas sur un block non translucide
                entityLocation.setX(entityLocation.getX() + relX);
                entityLocation.setY(entityLocation.getY() + relY);
            }
        }
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];

        for (int y = 0; y < this.getHeight(); y++) {
            String line = "";
            for (int x = 0; x < this.getLength(); x++) {
                try {
                    Block block = getBlock(x, y);
                    LivingEntity entity = getEntity(x, y);
                    if (entity != null) {
                        line += entity.getCharacter();
                    } else if (block != null) {
                        line += block.getCharacter();
                    } else {
                        line += ' ';
                    }
                } catch (CoordinatesOutOfBoundsException ex) {
                }
            }
            result[y] = line;
        }

        return result;
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
     * Méthode permettant de définir les pièces de l'étage
     *
     * @param rooms
     */
    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    /**
     * Méthode permettant de récupérer les entités dans l'étage
     *
     * @return La liste des entités
     */
    public LinkedList<LivingEntity> getEntities() {
        return entities;
    }
}
