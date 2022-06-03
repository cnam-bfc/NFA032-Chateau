package net.cnam.chateau.structure.block.decorative;

/**
 * Classe permettant de créer un block Lit
 */
public class Table extends DecorativeBlock {

    /**
     * Constructeur de la classe Table
     */
    public Table() {
        super("Table");
    }

    /**
     * Méthode permettant de retourner le caractère représentant le block
     */
    @Override
    public String getCharacter() {
        return "T";
    }
}
