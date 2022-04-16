package net.cnam.structure.block;

import net.cnam.object.DisplayableObject;
//import net.cnam.object.Location;

/**
 * Class permettant de créer un block pour la map.
 */
public class Block implements DisplayableObject {

    private final char character;
    //private Location location; // à voir si on met des coordonnées ou si on gère tout avec le tab à 2 dim

    public Block(char character) {
        this.character = character;
    }

    /**
     * Méthode pour récupérer un caractère représentant un bloc ou une entité
     *
     * @return caractère représentant le bloc ou l'entité
     */
    @Override
    public char getCharacter() {
        return character;
    }

}
