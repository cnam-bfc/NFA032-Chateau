package net.cnam.gui.component;

import net.cnam.utils.StringUtils;

public class CLabel extends CComponent {

    private String[] textLines;

    public CLabel(String text) {
        this(
                // String[] lines
                StringUtils.convertStringToStringArray(text),
                // length
                StringUtils.getMaximumLength(StringUtils.convertStringToStringArray(text)),
                // height
                StringUtils.convertStringToStringArray(text).length);
    }

    public CLabel(String text, int length, int height) {
        this(StringUtils.convertStringToStringArray(text), length, height);
    }

    public CLabel(String[] lines) {
        this(lines, StringUtils.getMaximumLength(lines), lines.length);
    }

    public CLabel(String[] lines, int length, int height) {
        super(length, height);
        this.textLines = lines;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - textLines.length;
        for (int i = 0; i < paddingHeight / 2; i++) {
            result[linePointer++] = emptyLine;
        }
        for (String textLine : textLines) {
            if (textLine.length() > this.getLength()) {
                textLine = textLine.substring(0, this.getLength());
            } else if (textLine.length() < this.getLength()) {
                textLine = StringUtils.centerString(textLine, ' ', this.getLength());
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
    public void keyPressed(int key) {
    }

    public String getText() {
        return StringUtils.convertStringArrayToString(textLines);
    }

    public void setText(String text) {
        this.textLines = StringUtils.convertStringToStringArray(text);
    }

    public void setText(String[] lines) {
        this.textLines = lines;
    }
}
