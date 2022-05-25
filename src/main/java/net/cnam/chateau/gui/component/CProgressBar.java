package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

public class CProgressBar extends CComponent {
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
        String line = CColor.REVERSE.getForeground();
        line += " ".repeat(progressed);
        line += CColor.REVERSE.getForegroundReset();
        line += " ".repeat(this.getLength() - progressed);

        for (int linePointer = 0; linePointer < result.length; linePointer++) {
            result[linePointer] = line;
        }

        return result;
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
