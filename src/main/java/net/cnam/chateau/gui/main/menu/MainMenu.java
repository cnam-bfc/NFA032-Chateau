package net.cnam.chateau.gui.main.menu;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CButtons;
import net.cnam.chateau.gui.DisplayableComponent;

public class MainMenu extends CFrame implements DisplayableComponent {

    private final CButtons buttonsChoices;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;

    public MainMenu(Console console) {
        super(new CLabel("Menu principal"));

        this.buttonsChoices = new CButtons(new CButton[]{
            new PlayButton(console, this),
            new SettingsButton(console),
            new QuitButton(this)
        }, 1);

        this.getContent().getContent().add(buttonsChoices);

        try {
            this.audioPlayer = new SimpleAudioPlayer("/songs/Stranger Things 3 - The Game Soundtrack - Hess Farm.wav");
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        }
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public SimpleAudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
