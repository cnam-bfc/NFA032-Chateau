package net.cnam.chateau.structure;

import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.structure.block.Block;

/**
 * Classe d'un étage
 */
public class Stage {

    private final List<Entity> entities = new LinkedList<>();
    private final Room[] rooms;
    private final int length;
    private final int height;

    /**
     * Constructeur
     *
     * @param rooms Tableau des pièces de l'étage
     * @param length La longueur de l'étage
     * @param height La hauteur de l'étage
     */
    public Stage(Room[] rooms, int length, int height) {
        this.rooms = rooms;
        this.length = length;
        this.height = height;
    }

    /**
     * Méthode pour récupérer le newBlock aux coordonnées passé en paramètres.
     *
     * @param location Coordonnées du block
     * @return Le bloc aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Block getBlock(Location location) throws CoordinatesOutOfBoundsException {
        return this.getBlock(location.getX(), location.getY());
    }

    /**
     * Méthode pour récupérer le newBlock aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return Le bloc aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Block getBlock(int x, int y) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x >= this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y >= this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        for (Room room : rooms) {
            Location roomLocation = room.getLocation();
            // Si le newBlock est dans la pièce
            if (x >= roomLocation.getX() && x < roomLocation.getX() + room.getLength()
                    && y >= roomLocation.getY() && y < roomLocation.getY() + room.getHeight()) {
                // On récupère le bloc en calculant ses coordonnées relatives par rapport à la pièce
                return room.getBlocks()[x - roomLocation.getX()][y - roomLocation.getY()];
            }
        }

        return null;
    }

    /**
     * Méthode pour récupérer la room aux coordonnées passé en paramètres.
     *
     * @param location Coordonnées
     * @return La pièce aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Room getRoom(Location location) throws CoordinatesOutOfBoundsException {
        return getRoom(location.getX(), location.getY());
    }

    /**
     * Méthode pour récupérer la room aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return La pièce aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Room getRoom(int x, int y) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x >= this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y >= this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        for (Room room : rooms) {
            Location roomLocation = room.getLocation();
            // Si les coordonnées sont dans la pièce
            if (x >= roomLocation.getX() && x < roomLocation.getX() + room.getLength()
                    && y >= roomLocation.getY() && y < roomLocation.getY() + room.getHeight()) {
                return room;
            }
        }

        return null;
    }

    /**
     * Méthode pour récupérer les room aux coordonnées passé en paramètres.
     *
     * @param location Coordonnées
     * @return Les pièce aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Room[] getRooms(Location location) throws CoordinatesOutOfBoundsException {
        return getRooms(location.getX(), location.getY());
    }

    /**
     * Méthode pour récupérer la room aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return Les pièces aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Room[] getRooms(int x, int y) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x >= this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y >= this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        List<Room> roomList = new LinkedList<>();

        for (Room room : rooms) {
            Location roomLocation = room.getLocation();
            // Si les coordonnées sont dans la pièce
            if (x >= roomLocation.getX() && x < roomLocation.getX() + room.getLength()
                    && y >= roomLocation.getY() && y < roomLocation.getY() + room.getHeight()) {
                roomList.add(room);
            }
        }

        return roomList.toArray(Room[]::new);
    }

    /**
     * Méthode pour définir un newBlock aux coordonnées passé en paramètres.
     *
     * @param location Coordonnées du block
     * @param block Le bloc aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public void setBlock(Location location, Block block) throws CoordinatesOutOfBoundsException {
        this.setBlock(location.getX(), location.getY(), block);
    }

    /**
     * Méthode pour définir un newBlock aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @param block Le bloc aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public void setBlock(int x, int y, Block block) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x >= this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y >= this.getHeight()) {
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
     * @param location Coordonnées dans l'étage
     * @return L'entité aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Entity getEntity(Location location) throws CoordinatesOutOfBoundsException {
        return this.getEntity(location.getX(), location.getY());
    }

    /**
     * Méthode pour récupérer l'entité aux coordonnées passé en paramètres.
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return L'entité aux coordonnées spécifiés
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception lorsque les coordonnées ne sont pas contenu dans la taille de
     * l'étage
     */
    public Entity getEntity(int x, int y) throws CoordinatesOutOfBoundsException {
        if (x < 0) {
            throw new CoordinatesOutOfBoundsException("x doit être positif");
        }
        if (y < 0) {
            throw new CoordinatesOutOfBoundsException("y doit être positif");
        }
        if (x >= this.getLength()) {
            throw new CoordinatesOutOfBoundsException("x doit inférieur à " + this.getLength() + " (" + x + ")");
        }
        if (y >= this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("y doit inférieur à " + this.getHeight() + " (" + y + ")");
        }

        for (Entity entity : entities) {
            Location entityLocation = entity.getLocation();
            if (entityLocation.getX() == x && entityLocation.getY() == y) {
                return entity;
            }
        }

        return null;
    }

    /**
     * Méthode permettant de récupérer les entités dans l'étage
     *
     * @return La liste des entités
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Méthode permettant de récupérer les pièces de l'étage
     *
     * @return Les pièces
     */
    public Room[] getRooms() {
        return rooms;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }
}
