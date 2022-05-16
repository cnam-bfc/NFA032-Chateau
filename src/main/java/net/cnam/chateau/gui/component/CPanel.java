package net.cnam.chateau.gui.component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.utils.direction.Orientation;

public class CPanel extends CComponent {

    private final List<CComponent> content = new LinkedList<>();

    private Orientation renderOrientation;
    private boolean renderMainPadding;

    public CPanel(int length, int height) {
        this(length, height, Orientation.VERTICAL, true);
    }

    public CPanel(int length, int height, Orientation renderOrientation, boolean renderMainPadding) {
        super(length, height);

        this.renderOrientation = renderOrientation;
        this.renderMainPadding = renderMainPadding;
    }

    public CPanel(CComponent[] components, Orientation renderOrientation, int spacing) {
        this(0, 0, renderOrientation, false);

        switch (renderOrientation) {
            case VERTICAL -> {
                // Calcul de la hauteur
                int height = 0;
                for (CComponent component : components) {
                    height += component.getHeight();
                }
                if (components.length != 0) {
                    height += (components.length - 1) * spacing;
                }
                this.setHeight(height);

                // Calcul de la longeur
                int length = 0;
                for (CComponent component : components) {
                    if (component.getLength() > length) {
                        length = component.getLength();
                    }
                }
                this.setLength(length);
            }
            case HORIZONTAL -> {
                // Calcul de la hauteur
                this.setHeight(1);

                // Calcul de la longeur
                int length = 0;
                for (CComponent component : components) {
                    length += component.getLength();
                }
                if (components.length != 0) {
                    length += (components.length - 1) * spacing;
                }
                this.setLength(length);
            }
        }
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        switch (renderOrientation) {
            case VERTICAL -> {
                int contentLines = 0;
                for (CComponent component : this.getContent()) {
                    contentLines += component.getHeight();
                }

                // Lignes du panel - lignes de texte au millieu
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
            }
            case HORIZONTAL -> {
                // Espace de padding vertical
                for (int i = 0; i < result.length / 2; i++) {
                    result[linePointer++] = emptyLine;
                }

                int contentColumns = 0;
                for (CComponent component : this.getContent()) {
                    contentColumns += component.getLength();
                }

                // Colonnes du panel - Colonnes du texte au millieu
                int paddingLength = this.getLength() - contentColumns;
                int paddingNb = this.getContent().size() - 1;
                if (renderMainPadding) {
                    paddingNb += 2;
                }

                String line = "";
                int lineLength = 0;
                Iterator<CComponent> componentIterator = this.getContent().iterator();
                mainLoop:
                for (int i = 0; componentIterator.hasNext(); i++) {
                    CComponent component = componentIterator.next();

                    // Espace de padding
                    // + 1 pour le padding automatique (bourage) de fin
                    if (i != 0 || renderMainPadding) {
                        for (int j = 0; j < paddingLength / paddingNb; j++) {
                            line += ' ';
                            lineLength++;
                        }
                    }

                    // Rendu du composant
                    if (lineLength + component.getLength() > this.getLength()) {
                        continue;
                    }
                    lineLength += component.getLength();
                    line += component.render()[0];
                }
                for (; lineLength < this.getLength(); lineLength++) {
                    line += ' ';
                }
                result[linePointer++] = line;
            }
        }

        // Bourage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    @Override
    public void onKeyPressed(int key) {
        for (CComponent component : content) {
            component.onKeyPressed(key);
        }
    }

    public List<CComponent> getContent() {
        return content;
    }

    public Orientation getRenderingOrientation() {
        return renderOrientation;
    }

    public void setRenderingOrientation(Orientation renderOrientation) {
        this.renderOrientation = renderOrientation;
    }

    public boolean isRenderingMainPadding() {
        return renderMainPadding;
    }

    public void setRenderingMainPadding(boolean renderMainPadding) {
        this.renderMainPadding = renderMainPadding;
    }
}
