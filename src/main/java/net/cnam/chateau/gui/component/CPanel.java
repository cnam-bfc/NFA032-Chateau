package net.cnam.chateau.gui.component;

import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.utils.direction.Orientation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CPanel extends CComponent implements KeyListener {
    private final List<CComponent> components = new LinkedList<>();

    private Orientation renderOrientation;
    private boolean renderMainPadding;

    public CPanel(int length, int height) {
        this(HorizontalAlignment.CENTER, length, height);
    }

    public CPanel(HorizontalAlignment horizontalAlignment, int length, int height) {
        this(horizontalAlignment, length, height, Orientation.VERTICAL, true);
    }

    public CPanel(int length, int height, Orientation renderOrientation, boolean renderMainPadding) {
        this(HorizontalAlignment.CENTER, length, height, renderOrientation, renderMainPadding);
    }

    public CPanel(HorizontalAlignment horizontalAlignment, int length, int height, Orientation renderOrientation, boolean renderMainPadding) {
        super(horizontalAlignment, length, height);

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

                // Calcul de la longueur
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

                // Calcul de la longueur
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

    // TODO Faire le rendu en fonction de l'alignement horizontal
    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        switch (renderOrientation) {
            case VERTICAL -> {
                int contentLines = 0;
                for (CComponent component : this.getComponents()) {
                    contentLines += component.getHeight();
                }

                // Lignes du panel - lignes de texte au milieu
                int paddingHeight = this.getHeight() - contentLines;
                int paddingNb = this.getComponents().size() - 1;
                if (renderMainPadding) {
                    paddingNb += 2;
                }

                Iterator<CComponent> componentsIterator = this.getComponents().iterator();
                mainLoop:
                for (int i = 0; componentsIterator.hasNext(); i++) {
                    CComponent component = componentsIterator.next();

                    // Espace de padding
                    // + 1 pour le padding automatique (bourrage) de fin
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
                for (CComponent component : this.getComponents()) {
                    contentColumns += component.getLength();
                }

                // Colonnes du panel - Colonnes du texte au millieu
                int paddingLength = this.getLength() - contentColumns;
                int paddingNb = this.getComponents().size() - 1;
                if (renderMainPadding) {
                    paddingNb += 2;
                }

                StringBuilder line = new StringBuilder();
                int lineLength = 0;
                Iterator<CComponent> componentIterator = this.getComponents().iterator();
                for (int i = 0; componentIterator.hasNext(); i++) {
                    CComponent component = componentIterator.next();

                    // Espace de padding
                    // + 1 pour le padding automatique (bourrage) de fin
                    if (i != 0 || renderMainPadding) {
                        for (int j = 0; j < paddingLength / paddingNb; j++) {
                            line.append(' ');
                            lineLength++;
                        }
                    }

                    // Rendu du composant
                    if (lineLength + component.getLength() > this.getLength()) {
                        continue;
                    }
                    lineLength += component.getLength();
                    line.append(component.render()[0]);
                }
                for (; lineLength < this.getLength(); lineLength++) {
                    line.append(' ');
                }
                result[linePointer++] = line.toString();
            }
        }

        // Bourrage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        for (CComponent component : components) {
            if (component instanceof KeyListener componentListener) {
                componentListener.onKeyPressed(event);
            }
        }
    }

    public List<CComponent> getComponents() {
        return components;
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
