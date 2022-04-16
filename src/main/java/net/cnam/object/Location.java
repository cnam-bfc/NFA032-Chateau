package net.cnam.object;

/**
 * Class permettant de stocker des coordonnées x,y 
 * 
 */
public class Location {
    
    private int x;
    private int z;

    /**
     * Constructeur
     * 
     * @param x coordonnée X
     * @param z coordonnée Z
     */
    public Location(int x, int z) {
        this.x = x;
        this.z = z;
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
     * Méthode permettant de modifier/définir la cordonnées X dans l'objet Location sous forme d'entier
     * 
     * @param x entier (pour cordonnée X)
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Méthode permettant de récupérer la cordonnées Z sous forme d'entier
     * 
     * @return un entier (coordonnée Z)
     */
    public int getZ() {
        return z;
    }

    /**
     * Méthode permettant de modifier/définir la cordonnées Z dans l'objet Location sous forme d'entier
     * 
     * @param z entier (pour cordonnée Z)
     */
    public void setZ(int z) {
        this.z = z;
    }
    
    

}
