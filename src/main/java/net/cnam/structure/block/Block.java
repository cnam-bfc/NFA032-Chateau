package net.cnam.structure.block;

import net.cnam.object.DisplayableObject;

/**
 * Class permettant de créer un block pour la map.
 */
public class Block implements DisplayableObject {

    private char caracter;

    public Block(char caracter) {
        this.caracter = caracter;
    }

    /**
     * Méthode pour récupérer un caractère représentant un bloc ou une entité
     *
     * @return caractère représentant le bloc ou l'entité
     */
    @Override
    public char getCharacter() {
        return caracter;
    }

}
