package net.cnam.gui.component;

import java.util.ArrayList;
import java.util.List;

public class CPanel extends CComponent {

    private final List<CComponent> content = new ArrayList<>();

    public CPanel(int length, int height) {
        super(length, height);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        int contentLines = 0;
        for (CComponent component : this.getContent()) {
            contentLines += component.getHeight();
        }

        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - contentLines;

        for (CComponent component : this.getContent()) {
            // Espace de padding
            // + 1 pour le padding automatique (bourage) de fin
            for (int j = 0; j < paddingHeight / (this.getContent().size() + 1); j++) {
                linePointer = renderAddLine(result, linePointer, emptyLine);
            }

            // Rendu du composant
            for (String componentLine : component.render()) {
                int paddingLength = this.getLength() - component.getLength();
                String line = "";
                if (paddingLength > 0) {
                    line += " ".repeat(paddingLength / 2);
                }
                line += componentLine;
                if (paddingLength > 0) {
                    line += " ".repeat(paddingLength / 2 + paddingLength % 2);
                }
                linePointer = renderAddLine(result, linePointer, line);
            }
        }

        // Bourage à la fin
        for (; linePointer < result.length;) {
            linePointer = renderAddLine(result, linePointer, emptyLine);
        }

        return result;
    }

    public List<CComponent> getContent() {
        return content;
    }
}
