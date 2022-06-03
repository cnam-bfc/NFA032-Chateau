package net.cnam.chateau.gui.main.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.information.InfoMenu;
import net.cnam.chateau.gui.main.menu.statistics.StatisticsMenu;
import net.cnam.chateau.gui.play.start.PlayMenu;
import net.cnam.chateau.gui.settings.menu.SettingsMenu;
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
            this.audioPlayer = app.createAudioPlayer(Music.MENU);
            audioPlayer.setVolume(app.getSettings().getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }

        CChoices choices = new CChoices(app, 1);

        choices.add(new OpenComponentButton(app, new PlayMenu(app, this), "Jouer"));
        choices.add(new OpenComponentButton(app, new StatisticsMenu(app), "Statistiques"));
        choices.add(new OpenComponentButton(app, new InfoMenu(app), "Informations"));
        choices.add(new OpenComponentButton(app, new SettingsMenu(app, audioPlayer), "Paramètres"));
        choices.add(new QuitComponentButton(app, this, "Quitter"));

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopLoopingMode() {
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
