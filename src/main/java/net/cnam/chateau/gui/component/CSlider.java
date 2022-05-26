package net.cnam.chateau.gui.component;

import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;

public class CSlider extends CProgressBar implements SelectableComponent, KeyListener {
    private boolean selected = true;

    public CSlider(int length, int height, int value, int maxValue, String text) {
        super(length, height, value, maxValue, text);
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
        if (isSelected()) {
            if (!this.getColors().contains(CColor.UNDERLINE)) {
                this.getColors().add(CColor.UNDERLINE);
            }
        } else {
            this.getColors().remove(CColor.UNDERLINE);
        }

        return super.render();
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
