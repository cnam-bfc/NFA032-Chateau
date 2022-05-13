package net.cnam.chateau.utils.direction;

/**
 * Exception levé lorsqu'une direction ne peux pas être trouvée
 */
public class DirectionNotFoundException extends Exception {

    /**
     * Constructeur
     *
     * @param msg Message d'erreur
     */
    public DirectionNotFoundException(String msg) {
        super(msg);
    }
}
