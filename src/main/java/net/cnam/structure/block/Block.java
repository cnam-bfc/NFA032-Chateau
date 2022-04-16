package net.cnam.structure.block;

/**
 * Class permettant de créer un block pour la map.
 * implémente
 * @see net.cnam.object.DisplayableObject
 */
public class Block implements CaracterBlockEntity {

    private char caracter;
    
    public Block(char caracter) {
        this.caracter = caracter;
    }
     
    /**
     * Méthode pour récupérer un caractère représentant un bloc ou une entité
     * @return caractère représentant le bloc ou l'entité
     */
    @Override
    public char getCaracter() {
        return caracter;
    }

}
