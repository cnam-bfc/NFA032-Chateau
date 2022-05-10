package net.cnam.gui.component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CPanel extends CComponent {

    private final List<CComponent> content = new LinkedList<>();

    private boolean renderMainPadding;

    public CPanel(int length, int height) {
        this(length, height, true);
    }

    public CPanel(int length, int height, boolean renderMainPadding) {
        super(length, height);

        this.renderMainPadding = renderMainPadding;
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
        int paddingNb = this.getContent().size() - 1;
        if (renderMainPadding) {
            paddingNb += 2;
        }

        Iterator<CComponent> componentIterator = this.getContent().iterator();
        mainLoop:
        for (int i = 0; componentIterator.hasNext(); i++) {
            CComponent component = componentIterator.next();

            // Espace de padding
            // + 1 pour le padding automatique (bourage) de fin
            if (i != 0 || renderMainPadding) {
                for (int j = 0; j < paddingHeight / paddingNb; j++) {
                    result[linePointer++] = emptyLine;
                }
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

                if (linePointer < result.length) {
                    result[linePointer++] = line;
                } else {
                    break mainLoop;
                }
            }
        }

        // Bourage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    @Override
    public void keyPressed(int key) {
        for (CComponent component : content) {
            component.keyPressed(key);
        }
    }

    public List<CComponent> getContent() {
        return content;
    }

    public boolean isRenderingMainPadding() {
        return renderMainPadding;
    }

    public void setRenderingMainPadding(boolean renderMainPadding) {
        this.renderMainPadding = renderMainPadding;
    }
}
