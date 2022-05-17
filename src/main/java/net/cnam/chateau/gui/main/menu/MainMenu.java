package net.cnam.chateau.gui.main.menu;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;

public class MainMenu extends CFrame implements DisplayableComponent {

    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;

    public MainMenu(Console console, AppSettings settings) {
        super(new CLabel("Menu principal"), 0, 0);

        try {
            this.audioPlayer = new SimpleAudioPlayer("/songs/Stranger Things 3 - The Game Soundtrack - Hess Farm.wav");
            audioPlayer.setVolume(settings.getMusicVolume());
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        }

        CChoices choices = new CChoices(new SelectableComponent[]{
            new PlayButton(console, settings, this),
            new SettingsButton(console, settings, audioPlayer),
            new QuitButton(this)
        }, 1);

        this.getContent().getContent().add(choices);
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
