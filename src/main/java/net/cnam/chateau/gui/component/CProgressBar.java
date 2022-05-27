package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CProgressBar extends CComponent {
    private final List<CColor> textColors = new LinkedList<>();
    private final List<CColor> progressedColors = new LinkedList<>();
    private final List<CColor> unprogressedColors = new LinkedList<>();
    private final String text;
    private int value;
    private int maxValue;

    public CProgressBar(int length, int height) {
        this(length, height, 0, 100, null);
    }

    public CProgressBar(int length, int height, int value, int maxValue, String text) {
        super(HorizontalAlignment.CENTER, length, height);

        this.value = value;
        this.maxValue = maxValue;
        this.text = text;
    }

    @Override
    public String[] render() {
        String textLine = text.replace("%VALUE%", value + "")
                .replace("%MAX_VALUE%", maxValue + "")
                .replace("%PERCENT%", (int) ((float) value / maxValue * 100) + " %");

        if (textLine.length() > getLength()) {
            textLine = textLine.substring(0, getLength());
        } else if (textLine.length() < getLength()) {
            int diff = getLength() - textLine.length();
            int left = diff / 2;
            int right = diff - left;
            textLine = " ".repeat(left) + textLine + " ".repeat(right);
        }

        String[] result = new String[this.getHeight()];

        int progressed = (int) ((float) value / maxValue * this.getLength());

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

        ListIterator<CColor> unprogressedColorIterator = unprogressedColors.listIterator();
        while (unprogressedColorIterator.hasNext()) {
            line.append(unprogressedColorIterator.next().getBackground());
        }
        line.append(textLine.substring(progressed));
        while (unprogressedColorIterator.hasPrevious()) {
            line.append(unprogressedColorIterator.previous().getBackgroundReset());
        }

        while (textColorsIterator.hasPrevious()) {
            line.append(textColorsIterator.previous().getForegroundReset());
        }

        for (int linePointer = 0; linePointer < result.length; linePointer++) {
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

    public List<CColor> getUnprogressedColors() {
        return unprogressedColors;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0) {
            value = 0;
        }
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        if (maxValue < 0) {
            maxValue = 0;
        }
        this.maxValue = maxValue;
    }
}
