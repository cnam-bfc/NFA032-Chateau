package net.cnam.chateau.gui.component;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class CButton extends CLabel implements SelectableComponent, KeyListener {
    private final App app;
    private boolean selected = false;

    public CButton(App app, String text) {
        this(app, HorizontalAlignment.CENTER, text);
    }

    public CButton(App app, HorizontalAlignment horizontalAlignment, String text) {
        super(horizontalAlignment, text);

        this.app = app;
    }

    public CButton(App app, String text, int length) {
        this(app, HorizontalAlignment.CENTER, text, length);
    }

    public CButton(App app, HorizontalAlignment horizontalAlignment, String text, int length) {
        super(horizontalAlignment, text, length);

        this.app = app;
    }

    public CButton(App app, String text, int length, int height) {
        this(app, HorizontalAlignment.CENTER, text, length, height);
    }

    public CButton(App app, HorizontalAlignment horizontalAlignment, String text, int length, int height) {
        super(horizontalAlignment, text, length, height);

        this.app = app;
    }

    public abstract void execute();

    @Override
    public String[] render() {
        if (selected) {
            if (!this.getColors().contains(CColor.REVERSE)) {
                this.getColors().add(CColor.REVERSE);
            }
        } else {
            this.getColors().remove(CColor.REVERSE);
        }

        return super.render();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        int key = event.getKey();

        // 10 = Entrée dans netbeans ; 13 = Entrée dans un terminal ; 32 = Espace
        if (this.isSelected() && (key == 10 || key == 13 || key == 32)) {
            try {
                SimpleAudioPlayer audioPlayer = app.createAudioPlayer(SoundEffect.SELECT);
                audioPlayer.setVolume(app.getSettings().getSoundEffectsVolume());
                audioPlayer.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                     IllegalArgumentException ignored) {
            }
            this.execute();
        }
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
