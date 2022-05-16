package net.cnam.chateau.gui.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

public class CChoises extends CPanel {

    private final List<SelectableComponent> selectableComponents;

    public CChoises(SelectableComponent[] components) {
        this(components, 0);
    }

    public CChoises(SelectableComponent[] components, int spacing) {
        super(Arrays.copyOf(components, components.length, CComponent[].class), Orientation.VERTICAL, spacing);

        for (int i = 0; i < components.length; i++) {
            SelectableComponent component = components[i];
            if (i == 0) {
                component.setSelected(true);
            } else {
                component.setSelected(false);
            }
            this.getContent().add((CComponent) component);
        }

        selectableComponents = new ArrayList<>(Arrays.asList(components));
    }

    @Override
    public void onKeyPressed(int key) {
        super.onKeyPressed(key);

        if (selectableComponents.size() < 2) {
            return;
        }

        try {
            Direction direction = DirectionUtils.parseDirection(key);
            switch (this.getRenderingOrientation()) {
                case VERTICAL -> {
                    if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
                        return;
                    }
                }
                case HORIZONTAL -> {
                    if (direction.equals(Direction.TOP) || direction.equals(Direction.BOTTOM)) {
                        return;
                    }
                }
            }
            switch (direction) {
                case TOP, LEFT -> {
                    for (int i = 0; i < selectableComponents.size(); i++) {
                        SelectableComponent components = selectableComponents.get(i);
                        if (components.isSelected()) {
                            components.setSelected(false);
                            if (i == 0) {
                                selectableComponents.get(selectableComponents.size() - 1).setSelected(true);
                            } else {
                                selectableComponents.get(i - 1).setSelected(true);
                            }
                            break;
                        }
                    }
                }
                case BOTTOM ,RIGHT -> {
                    for (int i = 0; i < selectableComponents.size(); i++) {
                        SelectableComponent components = selectableComponents.get(i);
                        if (components.isSelected()) {
                            components.setSelected(false);
                            if (i == selectableComponents.size() - 1) {
                                selectableComponents.get(0).setSelected(true);
                            } else {
                                selectableComponents.get(i + 1).setSelected(true);
                            }
                            break;
                        }
                    }
                }
            }
        } catch (DirectionNotFoundException ex) {
        }
    }
}
