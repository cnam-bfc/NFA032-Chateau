package net.cnam.utils.array;

/**
 * Exception levée lorsqu'un tableau est trop petit.
 */
public class ArrayTooSmallException extends Exception {
    /**
     * Constructeur
     *
     * @param message Message d'erreur
     */
    public ArrayTooSmallException(String message) {
        super(message);
    }
}
