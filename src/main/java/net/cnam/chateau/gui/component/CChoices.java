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
import java.util.List;

public class CChoices extends CPanel implements SelectableComponent, KeyListener {
    private final App app;
    private final List<SelectableComponent> selectableComponents = new ArrayList<>();

    private boolean selected = true;

    public CChoices(App app) {
        this(app, 0);
    }

    public CChoices(App app, int spacing) {
        this(app, Orientation.VERTICAL, spacing);
    }

    public CChoices(App app, Orientation orientation, int spacing) {
        super(HorizontalAlignment.CENTER, orientation, spacing);

        this.app = app;
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
                SimpleAudioPlayer audioPlayer = app.createAudioPlayer(SoundEffect.HOVER);
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

    public void add(SelectableComponent selectableComponent) {
        if (isSelected()) {
            if (selectableComponents.isEmpty()) {
                selectableComponent.setSelected(true);
            } else {
                selectableComponent.setSelected(false);
            }
        }
        selectableComponents.add(selectableComponent);
        CComponent component = (CComponent) selectableComponent;
        this.getComponents().add(component);
        this.autoResize();
    }

    public void remove(SelectableComponent selectableComponent) {
        if (selectableComponents.contains(selectableComponent)) {
            selectableComponents.remove(selectableComponent);
            CComponent component = (CComponent) selectableComponent;
            this.getComponents().remove(component);
        }
        this.autoResize();
    }

    public void removeAll() {
        for (SelectableComponent component : selectableComponents) {
            this.getComponents().remove((CComponent) component);
        }
        selectableComponents.clear();
    }
}
