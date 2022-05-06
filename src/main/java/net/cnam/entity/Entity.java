package net.cnam.entity;

import net.cnam.object.DisplayableObject;
import net.cnam.object.Location;

/**
 * Classe abstraite d'une entité
 */
public abstract class Entity implements DisplayableObject {

    private Location location;

    /**
     * Constructeur
     *
     * @param location Coordonnées de l'entité
     */
    public Entity(Location location) {
        this.location = location;
    }

    /**
     * Méthode permettant de récupérer les coordonnées de l'entité
     *
     * @return les coordonnées
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Méthode permettant de définir les coordonnées de l'entité
     *
     * @param location les nouvelles coordonnées
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
