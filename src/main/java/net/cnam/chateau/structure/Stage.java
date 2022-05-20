package net.cnam.chateau.structure;

import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.event.block.BlockListener;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.structure.block.Block;

/**
 * Classe d'un étage
 */
public class Stage {

    private final List<LivingEntity> entities = new LinkedList<>();

    private Room[] rooms;
    private int length;
    private int height;

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
    public LivingEntity getEntity(Location location) throws CoordinatesOutOfBoundsException {
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
    public LivingEntity getEntity(int x, int y) throws CoordinatesOutOfBoundsException {
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
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception levé si l'entitée tente de sortir de l'étage
     */
    public void move(LivingEntity entity, int relX, int relY) throws CoordinatesOutOfBoundsException {
        move(entity, new Location(entity.getLocation().getX() + relX, entity.getLocation().getY() + relY));
    }

    /**
     * Méthode permettant de faire bouger une entité dans l'étage
     *
     * @param entity L'entité à faire bouger
     * @param location Location où doit aller l'entité
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception levé si l'entitée tente de sortir de l'étage
     */
    public void move(LivingEntity entity, Location location) throws CoordinatesOutOfBoundsException {
        if (!entities.contains(entity)) {
            return;
        }

        Location entityLocation = entity.getLocation();
        Block oldBlock = this.getBlock(entityLocation.getX(), entityLocation.getY());
        if (location.getX() < 0 || location.getY() < 0 || location.getX() >= this.getLength() || location.getY() >= this.getHeight()) {
            throw new CoordinatesOutOfBoundsException("L'entité ne peut pas sortir de l'étage!");
        }

        Block newBlock = this.getBlock(location);

        // On notifie le nouveau block que l'entité rentre sur celui-ci
        if (newBlock != null && newBlock instanceof BlockListener blockListener) {
            EntityEnterBlockEvent event = new EntityEnterBlockEvent(entity);
            blockListener.onEntityEnterBlock(event);
            // Si le block refuse que l'entité rentre sur son territoire on ne déplace pas l'entité
            if (event.isCanceled()) {
                return;
            }
        }

        // On notifie l'ancien block où était l'entité que celle-ci est partie
        if (oldBlock != null && oldBlock instanceof BlockListener blockListener) {
            blockListener.onEntityLeaveBlock(new EntityLeaveBlockEvent(entity));
        }

        // On revérifie que l'entité est toujours dans l'étage car il se peut qu'un bloc comme un escalier ai fait changer l'entité d'étage
        if (!entities.contains(entity)) {
            return;
        }

        // On sauvegarde les coordonnées 
        Location possiblePetNewLocation = new Location(entityLocation.getX(), entityLocation.getY());

        // On déplace l'entité
        entityLocation.setX(location.getX());
        entityLocation.setY(location.getY());

        // Vérifier si l'entité est un joueur, si oui vérifie si il a un pet, si oui, positionne le pet à la position du joueur avant déplacement
        if (entity instanceof Player player) {
            if (player.havePet() && player.getPet().isFollowingPlayer() && !player.getPet().getLocation().equals(entityLocation)) {
                this.move(player.getPet(), possiblePetNewLocation);
            }
        }
    }

//    @Override
//    public String[] render() {
//        String[] result = new String[this.getHeight()];
//
//        for (int y = 0; y < this.getHeight(); y++) {
//            String line = "";
//            for (int x = 0; x < this.getLength(); x++) {
//                try {
//                    Block newBlock = getBlock(x, y);
//                    LivingEntity entity = getEntity(x, y);
//                    if (entity != null) {
//                        line += entity.getCharacter();
//                    } else if (newBlock != null) {
//                        line += newBlock.getCharacter();
//                    } else {
//                        line += ' ';
//                    }
//                } catch (CoordinatesOutOfBoundsException ex) {
//                }
//            }
//            result[y] = line;
//        }
//
//        return result;
//    }
    /**
     * Méthode permettant de récupérer les entités dans l'étage
     *
     * @return La liste des entités
     */
    public List<LivingEntity> getEntities() {
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

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Méthode permettant de définir les pièces de l'étage
     *
     * @param rooms
     */
    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
}
