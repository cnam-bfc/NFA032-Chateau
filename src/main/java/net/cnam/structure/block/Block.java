package net.cnam.structure.block;

public class Block implements CaracterBlockEntity {

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
    public char getCaracter() {
        return caracter;
    }

}
