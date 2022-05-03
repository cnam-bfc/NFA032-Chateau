package net.cnam.gui;

import java.util.ArrayList;
import java.util.List;
import net.cnam.utils.StringUtils;

public abstract class CComponent {

    private int length;
    private int height;

    private final List<CComponent> content = new ArrayList<>();

    public CComponent(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public String[] render() {
        String[] result = new String[height];
        int line = 0;
        String emptyLine = " ".repeat(length);

        int nbLinesToRender = 0;
        int nbComponentsToRender = content.size();
        String[][] componentsLines = new String[nbComponentsToRender][];
        for (int i = 0; i < content.size(); i++) {
            componentsLines[i] = content.get(i).render();
            nbLinesToRender += componentsLines[i].length;
        }

        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = height - nbLinesToRender;

        for (int i = 0; i < nbComponentsToRender; i++) {
            // + 1 pour le padding automatique (bourage) de fin
            for (int j = 0; j < paddingHeight / (nbComponentsToRender + 1); j++) {
                line = renderAddLine(result, line, emptyLine);
            }

            for (String componentsLine : componentsLines[i]) {
                line = renderAddLine(result, line, StringUtils.centerString(componentsLine, ' ', length));
            }
        }

        // Bourage à la fin
        for (; line < result.length;) {
            line = renderAddLine(result, line, emptyLine);
        }

        return result;
    }

    /**
     * Ajoute une ligne au rendu du composant graphique
     *
     * @param lines Tableau des lignes à rendre
     * @param linePointer Index où la ligne doit être ajouté
     * @param line Ligne à ajouter
     * @return L'index de la ligne suivante à ajouter
     */
    protected int renderAddLine(String[] lines, int linePointer, String line) {
        // On ne dépasse pas la hauteur maximale même si il reste des lignes à rendre (tant pis)
        if (linePointer == lines.length) {
            return linePointer;
        }

        lines[linePointer] = line.substring(0, length);
        return ++linePointer;
    }

    public void add(CComponent component) {
        content.add(component);
    }

    public void remove(CComponent component) {
        content.remove(component);
    }

    public List<CComponent> getContent() {
        return content;
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
}
