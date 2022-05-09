package net.cnam.utils.console;

public class CGraphics {

    /**
     * Ajoute une ligne au rendu du composant graphique
     *
     * @param lines Tableau des lignes à rendre
     * @param linePointer Index où la ligne doit être ajouté
     * @param line Ligne à ajouter
     * @return L'index de la ligne suivante à ajouter
     */
    public static int renderAddLine(String[] lines, int linePointer, String line) {
        // On ne dépasse pas la hauteur maximale même si il reste des lignes à rendre (tant pis)
        if (linePointer == lines.length) {
            return linePointer;
        }

        lines[linePointer] = line;
        return ++linePointer;
    }
}
