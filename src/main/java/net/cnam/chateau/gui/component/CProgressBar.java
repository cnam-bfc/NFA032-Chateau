package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CProgressBar extends CComponent {
    private final List<CColor> textColors = new LinkedList<>();
    private final List<CColor> progressedColors = new LinkedList<>();
    private final List<CColor> unProgressedColors = new LinkedList<>();
    private final String text;
    private int value;
    private int minValue;
    private int maxValue;

    public CProgressBar(int length, int height) {
        this(length, height, 0, 100, null);
    }

    public CProgressBar(int length, int height, int value, int maxValue, String text) {
        this(length, height, value, 0, maxValue, text);
    }

    public CProgressBar(int length, int height, int value, int minValue, int maxValue, String text) {
        super(HorizontalAlignment.CENTER, length, height);

        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.text = text;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];

        int progressed;
        if (minValue == maxValue) {
            progressed = this.getLength();
        } else {
            progressed = (int) ((float) (value - minValue) / (maxValue - minValue) * this.getLength());
        }

        String[] textLines = text.split("\n");

        for (int linePointer = 0; linePointer < result.length; linePointer++) {
            StringBuilder textLine = new StringBuilder();

            if (linePointer < textLines.length) {
                textLine = new StringBuilder(textLines[linePointer].replace("%VALUE%", value + "")
                        .replace("%MAX_VALUE%", maxValue + "")
                        .replace("%MIN_VALUE%", minValue + "")
                        .replace("%PERCENT%", (int) ((float) (value - minValue) / (maxValue - minValue) * 100) + " %"));

                String[] textSeparated = textLine.toString().split("%SEPARATOR%");
                if (textSeparated.length > 1) {
                    int allSeparatorsLength = this.getLength() - textLine.toString().replace("%SEPARATOR%", "").length();
                    int separatorLength = allSeparatorsLength / (textSeparated.length - 1);
                    int remainder = allSeparatorsLength % (textSeparated.length - 1);
                    textLine = new StringBuilder();
                    for (int i = 0; i < textSeparated.length; i++) {
                        textLine.append(textSeparated[i]);
                        if (i < textSeparated.length - 1) {
                            for (int j = 0; j < separatorLength; j++) {
                                if (remainder > 0) {
                                    textLine.append(" ");
                                    remainder--;
                                }
                                textLine.append(" ");
                            }
                        }
                    }
                }
            }

            if (textLine.length() > getLength()) {
                textLine = new StringBuilder(textLine.substring(0, getLength()));
            } else if (textLine.length() < getLength()) {
                int diff = getLength() - textLine.length();
                int left = diff / 2;
                int right = diff - left;
                textLine = new StringBuilder(" ".repeat(left) + textLine + " ".repeat(right));
            }

            StringBuilder line = new StringBuilder();

            ListIterator<CColor> textColorsIterator = textColors.listIterator();
            while (textColorsIterator.hasNext()) {
                line.append(textColorsIterator.next().getForeground());
            }

            ListIterator<CColor> progressedColorIterator = progressedColors.listIterator();
            while (progressedColorIterator.hasNext()) {
                line.append(progressedColorIterator.next().getBackground());
            }
            line.append(textLine.substring(0, progressed));
            while (progressedColorIterator.hasPrevious()) {
                line.append(progressedColorIterator.previous().getBackgroundReset());
            }

            ListIterator<CColor> unProgressedColorIterator = unProgressedColors.listIterator();
            while (unProgressedColorIterator.hasNext()) {
                line.append(unProgressedColorIterator.next().getBackground());
            }
            line.append(textLine.substring(progressed));
            while (unProgressedColorIterator.hasPrevious()) {
                line.append(unProgressedColorIterator.previous().getBackgroundReset());
            }

            while (textColorsIterator.hasPrevious()) {
                line.append(textColorsIterator.previous().getForegroundReset());
            }

            result[linePointer] = line.toString();
        }

        return result;
    }

    public List<CColor> getTextColors() {
        return textColors;
    }

    public List<CColor> getProgressedColors() {
        return progressedColors;
    }

    public List<CColor> getUnProgressedColors() {
        return unProgressedColors;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < minValue) {
            this.value = minValue;
        } else if (value > maxValue) {
            this.value = maxValue;
        } else {
            this.value = value;
        }
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
