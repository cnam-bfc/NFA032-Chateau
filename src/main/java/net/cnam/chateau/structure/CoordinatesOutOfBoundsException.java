package net.cnam.chateau.structure;

/**
 * Exception levée lorsque les cordonnées sont hors champs.
 */
public class CoordinatesOutOfBoundsException extends Exception {

    /**
     * Constructeur
     *
     * @param message Message d'erreur
     */
    public CoordinatesOutOfBoundsException(String message) {
        super(message);
    }
}
