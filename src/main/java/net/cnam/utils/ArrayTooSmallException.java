package net.cnam.utils;

/**
 * Exception levée lorsqu'un tableau est trop petit.
 */
public class ArrayTooSmallException extends Exception {
    public ArrayTooSmallException(String message) {
        super(message);
    }
}
