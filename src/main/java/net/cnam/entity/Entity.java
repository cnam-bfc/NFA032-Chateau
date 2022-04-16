package net.cnam.entity;

import net.cnam.object.DisplayableObject;
import net.cnam.object.Location;

/**
 * Classe abstraite d'une entité
 */
public abstract class Entity implements DisplayableObject {

    private final char character;
    private Location location;

    /**
     * Constructeur
     *
     * @param character Caractère utilisé lors de l'affichage de l'entité
     * @param location Coordonnées de l'entité
     */
    public Entity(char character, Location location) {
        this.character = character;
        this.location = location;
    }

    /**
     * Méthode permettant de récupérer le caractère à afficher quand on veut
     * afficher l'entité
     *
     * @return le caractère
     */
    @Override
    public char getCharacter() {
        return character;
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
