package net.cnam.gui.component;

import net.cnam.utils.StringUtils;

public abstract class CComponent {

    private int length;
    private int height;

    public CComponent(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public abstract String[] render();
    
    public void setSize(int length, int height) {
        this.setLength(length);
        this.setHeight(height);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return StringUtils.convertStringArrayToString(render());
    }

    /**
     * Ajoute une ligne au rendu du composant graphique
     *
     * @param lines Tableau des lignes à rendre
     * @param linePointer Index où la ligne doit être ajouté
     * @param line Ligne à ajouter
     * @return L'index de la ligne suivante à ajouter
     */
    protected static int renderAddLine(String[] lines, int linePointer, String line) {
        // On ne dépasse pas la hauteur maximale même si il reste des lignes à rendre (tant pis)
        if (linePointer == lines.length) {
            return linePointer;
        }

        lines[linePointer] = line;
        return ++linePointer;
    }
}
