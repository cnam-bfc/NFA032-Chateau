package net.cnam.chateau.utils.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * SimpleAudioPlayer is a class to play a song file.
 * <p>
 * Source: https://www.geeksforgeeks.org/play-audio-file-using-java/
 *
 * @author GeeksforGeeks
 */
public class SimpleAudioPlayer {
    private final Clip clip;
    private final String filePath;

    // to store current position
    private Long currentFrame;

    // current status of clip
    private Status status = Status.NONE;
    private boolean loop = false;
    private float volume = 1f;

    // constructor to initialize streams and clip
    public SimpleAudioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException, IllegalArgumentException {
        this.clip = AudioSystem.getClip();
        this.filePath = filePath;

        resetAudioStream();
    }

    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();

        status = Status.PLAYING;
    }

    // Method to pause the audio
    public void pause() {
        if (!status.equals(Status.PLAYING)) {
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        clip.close();
        status = Status.PAUSED;
    }

    // Method to resume the audio
    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (!status.equals(Status.PAUSED)) {
            return;
        }
        clip.stop();
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.stop();
        resetAudioStream();
        this.play();
    }

    // Method to stop the audio
    public void stop() {
        if (!(status.equals(Status.PLAYING) || status.equals(Status.PAUSED))) {
            return;
        }

        clip.stop();
        clip.close();
        currentFrame = 0L;
        status = Status.STOPPED;
    }

    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            this.stop();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to set volume 0.0 is 0% and 1.0 is 100%
    public void setVolume(float volume) {
        this.volume = volume;
        float db = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        FloatControl c = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        c.setValue(db);
    }

    // Method to set loop mode
    public void setLoop(boolean loopMode) {
        this.loop = loopMode;
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.loop(0);
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SimpleAudioPlayer.class.getResourceAsStream(filePath)));
        clip.open(audioInputStream);
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        float db = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        FloatControl c = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        c.setValue(db);
    }

    public enum Status {
        STOPPED,
        PAUSED,
        PLAYING,
        NONE
    }
}
