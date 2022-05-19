package net.cnam.chateau.utils;

/**
 * Class permettant de stocker des coordonnées x,y
 *
 */
public class Location extends Object {

    private int x;
    private int y;

    /**
     * Constructeur
     *
     * @param x coordonnée X
     * @param y coordonnée Y
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Méthode permettant de récupérer la cordonnées X sous forme d'entier
     *
     * @return un entier (coordonnée X)
     */
    public int getX() {
        return x;
    }

    /**
     * Méthode permettant de modifier/définir la cordonnées X dans l'objet
     * Location sous forme d'entier
     *
     * @param x entier (pour cordonnée X)
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Méthode permettant de récupérer la cordonnées Y sous forme d'entier
     *
     * @return un entier (coordonnée Y)
     */
    public int getY() {
        return y;
    }

    /**
     * Méthode permettant de modifier/définir la cordonnées Y dans l'objet
     * Location sous forme d'entier
     *
     * @param y entier (pour cordonnée Y)
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
}
