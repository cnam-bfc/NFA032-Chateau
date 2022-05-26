package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CProgressBar extends CComponent {
    private final List<CColor> colors = new LinkedList<>();
    private int value;
    private int maxValue;

    public CProgressBar(int length, int height) {
        this(length, height, 0, 100);
    }

    public CProgressBar(int length, int height, int value, int maxValue) {
        super(HorizontalAlignment.CENTER, length, height);

        this.value = value;
        this.maxValue = maxValue;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];

        int progressed = (int) ((float) value / maxValue * this.getLength());
        StringBuilder line = new StringBuilder(CColor.REVERSE.getForeground());
        ListIterator<CColor> colorIterator = colors.listIterator();
        while (colorIterator.hasNext()) {
            line.append(colorIterator.next().getForeground());
        }
        line.append(" ".repeat(progressed));
        while (colorIterator.hasPrevious()) {
            line.append(colorIterator.previous().getForegroundReset());
        }
        line.append(CColor.REVERSE.getForegroundReset());
        line.append(" ".repeat(this.getLength() - progressed));

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
