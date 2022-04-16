package net.cnam.entity;

import net.cnam.object.DisplayableObject;

/**
 * Classe abstraite d'une entité
 *
 * @TODO Coordonnées de l'entité
 */
public abstract class Entity implements DisplayableObject {

    private final char character;

    /**
     * Constructeur
     *
     * @param character Caractère utilisé lors de l'affichage de l'entité
     */
    public Entity(char character) {
        this.character = character;
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
}
