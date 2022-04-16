package net.cnam.structure.block;

import net.cnam.object.DisplayableObject;

public class Block implements DisplayableObject {

//CHAMPS
    private char caracter;
    
//CONSTRUCTEUR
    public Block(char caracter) {
        this.caracter = caracter;
    }
     
//METHODES
    /**
     * Méthode pour récupérer un caractère représentant un bloc ou une entité
     * @return caractère représentant le bloc ou l'entité
     */
    @Override
    public char getCharacter() {
        return caracter;
    }

}
