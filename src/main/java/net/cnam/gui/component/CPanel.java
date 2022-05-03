package net.cnam.gui.component;

import java.util.ArrayList;
import java.util.List;
import net.cnam.utils.StringUtils;

public class CPanel extends CComponent {

    private final List<CComponent> content = new ArrayList<>();

    public CPanel(int length, int height) {
        super(length, height);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int line = 0;
        String emptyLine = " ".repeat(this.getLength());

        int nbLinesToRender = 0;
        int nbComponentsToRender = content.size();
        String[][] componentsLines = new String[nbComponentsToRender][];
        for (int i = 0; i < content.size(); i++) {
            componentsLines[i] = content.get(i).render();
            nbLinesToRender += componentsLines[i].length;
        }

        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - nbLinesToRender;

        for (int i = 0; i < nbComponentsToRender; i++) {
            // + 1 pour le padding automatique (bourage) de fin
            for (int j = 0; j < paddingHeight / (nbComponentsToRender + 1); j++) {
                line = renderAddLine(result, line, emptyLine);
            }

            for (String componentsLine : componentsLines[i]) {
                line = renderAddLine(result, line, StringUtils.centerString(componentsLine, ' ', this.getLength()));
            }
        }

        // Bourage à la fin
        for (; line < result.length;) {
            line = renderAddLine(result, line, emptyLine);
        }

        return result;
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
}
