package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.StringUtils;

public abstract class CButton extends CLabel implements SelectableComponent {

    private boolean selected = false;

    public CButton(String text) {
        super(text);
    }

    public CButton(String text, int length, int height) {
        super(text, length, height);
    }

    public CButton(String[] lines) {
        super(lines);
    }

    public CButton(String[] lines, int length, int height) {
        super(lines, length, height);
    }

    public abstract void execute();

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        String[] textLines = StringUtils.convertStringToStringArray(this.getText());
        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - textLines.length;
        for (int i = 0; i < paddingHeight / 2; i++) {
            result[linePointer++] = emptyLine;
        }
        for (String textLine : textLines) {
            if (textLine.length() > this.getLength()) {
                if (selected) {
                    textLine = CColor.REVERSE + textLine.substring(0, this.getLength()) + CColor.REVERSE.getForegroundReset();
                } else {
                    textLine = textLine.substring(0, this.getLength());
                }
            } else if (textLine.length() < this.getLength()) {
                if (selected) {
                    int newLength = this.getLength() + CColor.REVERSE.getForeground().length() + CColor.REVERSE.getForegroundReset().length();
                    textLine = StringUtils.centerString(CColor.REVERSE + textLine + CColor.REVERSE.getForegroundReset(), ' ', newLength);
                } else {
                    textLine = StringUtils.centerString(textLine, ' ', this.getLength());
                }
            } else {
                if (selected) {
                    textLine = CColor.REVERSE + textLine + CColor.REVERSE.getForegroundReset();
                }
            }

            if (linePointer < result.length) {
                result[linePointer++] = textLine;
            } else {
                break;
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
        super.onKeyPressed(key);

        // 10 = Entrée dans netbeans ; 13 = Entrée dans un terminal
        if (this.isSelected() && (key == 10 || key == 13)) {
            this.execute();
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
