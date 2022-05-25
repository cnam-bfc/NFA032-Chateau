package net.cnam.chateau.gui.component;

import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

public class CGauge extends CFrame implements SelectableComponent, KeyListener {
    private final CLabel label;
    private final CProgressBar progressBar;
    private boolean selected = true;

    public CGauge(int length) {
        this(length, 0, 100);
    }

    public CGauge(int length, int value, int maxValue) {
        super(length, 3);

        this.label = new CLabel((int) ((float) value / maxValue * 100) + "%", 4, 1);
        this.progressBar = new CProgressBar(length - 7, 1, value, maxValue);

        CPanel panel = this.getContentPane();
        panel.setRenderingMainPadding(false);
        panel.setRenderingOrientation(Orientation.HORIZONTAL);
        panel.getComponents().add(label);
        panel.getComponents().add(progressBar);
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if (!isSelected()) {
            return;
        }

        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey());
            int value = getValue();
            switch (direction) {
                case LEFT -> {
                    value--;
                    if (value < 0) {
                        return;
                    }
                    setValue(value);
                }
                case RIGHT -> {
                    int maxValue = getMaxValue();
                    value++;
                    if (value > maxValue) {
                        return;
                    }
                    setValue(value);
                }
            }
        } catch (DirectionNotFoundException ignored) {
        }
    }

    @Override
    public String[] render() {
        String[] result = super.render();

        if (isSelected()) {
            String line = result[result.length - 2];
            result[result.length - 2] = line.substring(0, 1)
                    + CColor.REVERSE
                    + line.substring(1, line.length() - 1)
                    + CColor.REVERSE.getForegroundReset()
                    + line.substring(line.length() - 1);
        }

        return result;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getValue() {
        return progressBar.getValue();
    }

    public void setValue(int value) {
        progressBar.setValue(value);
        label.setText((int) ((float) getValue() / getMaxValue() * 100) + "%");
    }

    public int getMaxValue() {
        return progressBar.getMaxValue();
    }

    public void setMaxValue(int maxValue) {
        progressBar.setMaxValue(maxValue);
        label.setText((int) ((float) getValue() / getMaxValue() * 100) + "%");
    }
}
