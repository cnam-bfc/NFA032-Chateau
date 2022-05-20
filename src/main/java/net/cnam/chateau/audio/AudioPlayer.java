package net.cnam.chateau.audio;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

public class AudioPlayer {

    public static float volume;

    public static void play(SoundEffect soundEffect) {
        try {
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(soundEffect.getFilePath());
            audioPlayer.setVolume(volume);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException ex) {
        }
    }
}
