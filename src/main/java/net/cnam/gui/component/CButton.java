package net.cnam.gui.component;

import net.cnam.utils.StringUtils;

public class CButton extends CLabel {

    private boolean selected = false;
    private boolean disabled = false;

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

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int line = 0;
        String emptyLine = " ".repeat(this.getLength());

        String[] textLines = StringUtils.convertStringToStringArray(this.getText());
        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - textLines.length;
        for (int i = 0; i < paddingHeight / 2; i++) {
            line = this.renderAddLine(result, line, emptyLine);
        }
        for (String textLine : textLines) {
            if (textLine.length() > this.getLength()) {
                textLine = textLine.substring(0, this.getLength());
            } else if (textLine.length() < this.getLength()) {
                textLine = StringUtils.centerString(textLine, ' ', this.getLength());
            }
            if (selected) {
                line = this.renderAddLine(result, line, "\u001b[7m" + textLine + "\u001b[27m");
            } else {
                line = this.renderAddLine(result, line, textLine);
            }
        }

        // Bourage à la fin
        for (; line < result.length;) {
            line = renderAddLine(result, line, emptyLine);
        }

        return result;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
