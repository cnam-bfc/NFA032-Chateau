package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MainMenu extends CFrame implements DisplayableComponent {
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;

    public MainMenu(App app) {
        super(0, 0, "Menu principal");

        try {
            this.audioPlayer = new SimpleAudioPlayer(Music.MENU.getFilePath());
            audioPlayer.setVolume(app.getSettings().getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }

        CChoices choices = new CChoices(new SelectableComponent[]{
                new PlayButton(app, this),
                new SettingsButton(app, audioPlayer),
                new QuitButton(this)
        }, 1);

        this.getContentPane().getComponents().add(choices);
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
