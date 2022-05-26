package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CProgressBar extends CComponent {
    private final List<CColor> colors = new LinkedList<>();
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
        StringBuilder line = new StringBuilder(CColor.REVERSE.getForeground());
        ListIterator<CColor> colorIterator = colors.listIterator();
        while (colorIterator.hasNext()) {
            line.append(colorIterator.next().getForeground());
        }
        line.append(textLine.substring(0, progressed));
        while (colorIterator.hasPrevious()) {
            line.append(colorIterator.previous().getForegroundReset());
        }
        line.append(CColor.REVERSE.getForegroundReset());
        line.append(textLine.substring(progressed));

        for (int linePointer = 0; linePointer < result.length; linePointer++) {
            result[linePointer] = line.toString();
        }

        return result;
    }

    public List<CColor> getColors() {
        return colors;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
