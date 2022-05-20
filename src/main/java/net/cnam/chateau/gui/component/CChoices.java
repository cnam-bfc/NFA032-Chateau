package net.cnam.chateau.gui.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.cnam.chateau.gui.event.key.KeyEvent;
import net.cnam.chateau.gui.event.key.KeyListener;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

public class CChoices extends CPanel implements SelectableComponent, KeyListener {

    private final List<SelectableComponent> selectableComponents;

    private boolean selected = true;

    public CChoices(SelectableComponent[] components) {
        this(components, 0);
    }

    public CChoices(SelectableComponent[] components, int spacing) {
        this(components, Orientation.VERTICAL, spacing);
    }

    public CChoices(SelectableComponent[] components, Orientation orientation, int spacing) {
        super(Arrays.copyOf(components, components.length, CComponent[].class), orientation, spacing);

        for (int i = 0; i < components.length; i++) {
            SelectableComponent component = components[i];
            if (i == 0) {
                component.setSelected(true);
            } else {
                component.setSelected(false);
            }
            this.getComponents().add((CComponent) component);
        }

        selectableComponents = new ArrayList<>(Arrays.asList(components));
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        if (!isSelected()) {
            return;
        }

        super.onKeyPressed(event);

        if (selectableComponents.size() < 2) {
            return;
        }

        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey());
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

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            if (selectableComponents.isEmpty()) {
                return;
            }
            selectableComponents.get(0).setSelected(true);
        } else {
            for (SelectableComponent component : selectableComponents) {
                component.setSelected(false);
            }
        }
    }

    public void select(SelectableComponent selectableComponent) {
        if (!selectableComponents.contains(selectableComponent)) {
            return;
        }

        for (SelectableComponent comp : selectableComponents) {
            if (selectableComponent == comp) {
                comp.setSelected(true);
            } else {
                comp.setSelected(false);
            }
        }
    }
}
