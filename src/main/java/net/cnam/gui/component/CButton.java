package net.cnam.gui.component;

import net.cnam.utils.StringUtils;

public abstract class CButton extends CLabel {

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
            linePointer = this.renderAddLine(result, linePointer, emptyLine);
        }
        for (String textLine : textLines) {
            if (textLine.length() > this.getLength()) {
                if (selected) {
                    textLine = "\u001b[7m" + textLine.substring(0, this.getLength()) + "\u001b[27m";
                } else {
                    textLine = textLine.substring(0, this.getLength());
                }
            } else if (textLine.length() < this.getLength()) {
                if (selected) {
                    int newLength = this.getLength() + "\u001b[7m".length() + "\u001b[27m".length();
                    textLine = StringUtils.centerString("\u001b[7m" + textLine + "\u001b[27m", ' ', newLength);
                } else {
                    textLine = StringUtils.centerString(textLine, ' ', this.getLength());
                }
            } else {
                if (selected) {
                    textLine = "\u001b[7m" + textLine + "\u001b[27m";
                }
            }
            linePointer = this.renderAddLine(result, linePointer, textLine);
        }

        // Bourage à la fin
        for (; linePointer < result.length;) {
            linePointer = renderAddLine(result, linePointer, emptyLine);
        }

        return result;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
