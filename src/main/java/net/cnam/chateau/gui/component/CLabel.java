package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class CLabel extends CComponent {
    private final List<CColor> colors = new LinkedList<>();
    private String[] textLines;

    public CLabel(String text) {
        this(HorizontalAlignment.CENTER, text);
    }

    public CLabel(HorizontalAlignment horizontalAlignment, String text) {
        this(horizontalAlignment, StringUtils.convertStringToStringArray(text), StringUtils.getMaximumLength(StringUtils.convertStringToStringArray(text)), StringUtils.convertStringToStringArray(text).length);
    }

    public CLabel(String text, int length) {
        this(HorizontalAlignment.CENTER, text, length);
    }

    public CLabel(HorizontalAlignment horizontalAlignment, String text, int length) {
        this(horizontalAlignment, formatLines(StringUtils.convertStringToStringArray(text), length), length, formatLines(StringUtils.convertStringToStringArray(text), length).length);
    }

    public CLabel(String text, int length, int height) {
        this(HorizontalAlignment.CENTER, text, length, height);
    }

    public CLabel(HorizontalAlignment horizontalAlignment, String text, int length, int height) {
        this(horizontalAlignment, StringUtils.convertStringToStringArray(text), length, height);
    }

    public CLabel(String[] lines) {
        this(HorizontalAlignment.CENTER, lines);
    }

    public CLabel(HorizontalAlignment horizontalAlignment, String[] lines) {
        this(horizontalAlignment, lines, StringUtils.getMaximumLength(lines), lines.length);
    }

    public CLabel(String[] lines, int length) {
        this(HorizontalAlignment.CENTER, lines, length);
    }

    public CLabel(HorizontalAlignment horizontalAlignment, String[] lines, int length) {
        this(horizontalAlignment, formatLines(lines, length), length, formatLines(lines, length).length);
    }

    public CLabel(String[] lines, int length, int height) {
        this(HorizontalAlignment.CENTER, lines, length, height);
    }

    public CLabel(HorizontalAlignment horizontalAlignment, String[] lines, int length, int height) {
        super(horizontalAlignment, length, height);

        this.textLines = lines;
    }

    private static String[] formatLines(String[] lines, int length) {
        List<String> linesFormatted = new LinkedList<>();

        for (String line : lines) {
            boolean first = true;
            while (line.length() > length) {
                first = false;
                linesFormatted.add(line.substring(0, length));
                line = line.substring(length);
            }

            if (!line.isEmpty()) {
                if (!first) {
                    line = line + " ".repeat(length - line.length());
                }
                linesFormatted.add(line);
            }
        }

        return linesFormatted.toArray(String[]::new);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        // Lignes de la console - lignes de texte au milieu
        int paddingHeight = this.getHeight() - textLines.length;
        for (int i = 0; i < paddingHeight / 2; i++) {
            result[linePointer++] = emptyLine;
        }
        for (String textLine : textLines) {
            int lineLength = textLine.length();
            int length = this.getLength();
            for (CColor color : colors) {
                length += color.getForeground().length() + color.getForegroundReset().length();
                textLine = color.getForeground() + textLine + color.getForegroundReset();
            }
            if (lineLength > this.getLength()) {
                textLine = textLine.substring(0, length);
            } else if (lineLength < this.getLength()) {
                switch (this.getHorizontalAlignment()) {
                    case LEFT -> textLine += " ".repeat(length - textLine.length());
                    case CENTER -> textLine = StringUtils.centerString(textLine, ' ', length);
                    case RIGHT -> textLine = " ".repeat(length - textLine.length()) + textLine;
                }
            }

            if (linePointer < result.length) {
                result[linePointer++] = textLine;
            } else {
                break;
            }
        }

        // Bourrage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    public List<CColor> getColors() {
        return colors;
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
