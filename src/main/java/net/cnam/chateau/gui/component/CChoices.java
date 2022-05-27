package net.cnam.chateau.gui.component;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CChoices extends CPanel implements SelectableComponent, KeyListener {
    private final App app;
    private final List<SelectableComponent> selectableComponents;

    private boolean selected = true;

    public CChoices(App app, SelectableComponent[] components) {
        this(app, components, 0);
    }

    public CChoices(App app, SelectableComponent[] components, int spacing) {
        this(app, components, Orientation.VERTICAL, spacing);
    }

    public CChoices(App app, SelectableComponent[] components, Orientation orientation, int spacing) {
        super(Arrays.copyOf(components, components.length, CComponent[].class), orientation, spacing);

        this.app = app;

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
    public void onKeyPressed(KeyPressedEvent event) {
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
                case BOTTOM, RIGHT -> {
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
            try {
                SimpleAudioPlayer audioPlayer = app.createAudioPlayer(SoundEffect.HOVER.getAudioFile());
                audioPlayer.setVolume(app.getSettings().getSoundEffectsVolume());
                audioPlayer.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                     IllegalArgumentException ignored) {
            }
        } catch (DirectionNotFoundException ignored) {
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
