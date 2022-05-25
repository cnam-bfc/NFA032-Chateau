package net.cnam.chateau.gui.component;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class CButton extends CLabel implements SelectableComponent, KeyListener {
    private final AppSettings appSettings;
    private boolean selected = false;

    public CButton(AppSettings settings, String text) {
        this(settings, HorizontalAlignment.CENTER, text);
    }

    public CButton(AppSettings settings, HorizontalAlignment horizontalAlignment, String text) {
        super(horizontalAlignment, text);

        this.appSettings = settings;
    }

    public CButton(AppSettings settings, String text, int length) {
        this(settings, HorizontalAlignment.CENTER, text, length);
    }

    public CButton(AppSettings settings, HorizontalAlignment horizontalAlignment, String text, int length) {
        super(horizontalAlignment, text, length);

        this.appSettings = settings;
    }

    public CButton(AppSettings settings, String text, int length, int height) {
        this(settings, HorizontalAlignment.CENTER, text, length, height);
    }

    public CButton(AppSettings settings, HorizontalAlignment horizontalAlignment, String text, int length, int height) {
        super(horizontalAlignment, text, length, height);

        this.appSettings = settings;
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

        // 10 = Entrée dans netbeans ; 13 = Entrée dans un terminal
        if (this.isSelected() && (key == 10 || key == 13)) {
            try {
                SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(SoundEffect.SELECT.getFilePath());
                audioPlayer.setVolume(appSettings.getSoundEffectsVolume());
                audioPlayer.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException ignored) {
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
