package net.cnam.chateau.structure.block.decorative;

/**
 * Classe permettant de créer un block Bureau
 */
public class Desk extends DecorativeBlock {

    /**
     * Constructeur de la classe Bureau
     */
    public Desk() {
        super("Bureau");
    }

    /**
     * Méthode permettant de retourner le caractère représentant le block
     * @return le caractère représentant le block
     */
    @Override
    public String getCharacter() {
        return "D";
    }
}
