package net.cnam.chateau.structure.block.decorative;

/**
 * Classe permettant de créer un block Siège
 */
public class Seat extends DecorativeBlock {

    /**
     * Constructeur de la classe
     */
    public Seat() {
        super("Chaise");
    }

    /**
     * Retourne le caractère représentant le block
     * @return Le caractère représentant le block
     */
    @Override
    public String getCharacter() {
        return "S";
    }
}
