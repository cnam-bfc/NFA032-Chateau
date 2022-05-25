package net.cnam.chateau;

import java.io.*;

public class AppSettings {
    public static final int CONSOLE_MIN_LENGTH = 80;
    public static final int CONSOLE_MIN_HEIGHT = 25;
    public static final String DEFAULT_FILE_PATH = "settings.dat";

    private int consoleLength = CONSOLE_MIN_LENGTH;
    private int consoleHeight = CONSOLE_MIN_HEIGHT;
    private float musicVolume = 1f;
    private float soundEffectsVolume = 1f;

    public AppSettings() {
        String detectedConsoleLengthStr = System.getProperty("COLUMNS");
        if (detectedConsoleLengthStr != null) {
            int detectedConsoleLength = Integer.parseInt(detectedConsoleLengthStr);
            if (detectedConsoleLength > consoleLength) {
                consoleLength = detectedConsoleLength;
            }
        }
        String detectedConsoleHeightStr = System.getProperty("LINES");
        if (detectedConsoleHeightStr != null) {
            int detectedConsoleHeight = Integer.parseInt(detectedConsoleHeightStr);
            if (detectedConsoleHeight > consoleHeight) {
                consoleHeight = detectedConsoleHeight;
            }
        }
    }

    public void save(File file) throws IOException {
        FileOutputStream fluxEntree = new FileOutputStream(file);
        BufferedOutputStream outTampon = new BufferedOutputStream(fluxEntree);
        DataOutputStream out = new DataOutputStream(outTampon);

        out.writeInt(this.consoleLength);
        out.writeInt(this.consoleHeight);
        out.writeFloat(this.musicVolume);
        out.writeFloat(this.soundEffectsVolume);

        out.close();
    }

    public void load(File file) throws IOException {
        FileInputStream fluxEntree = new FileInputStream(file);
        BufferedInputStream inTampon = new BufferedInputStream(fluxEntree);
        DataInputStream in = new DataInputStream(inTampon);

        if (in.available() < 4) {
            in.close();
            return;
        }

        setConsoleLength(in.readInt());
        setConsoleHeight(in.readInt());
        setMusicVolume(in.readFloat());
        setSoundEffectsVolume(in.readFloat());

        in.close();
    }

    public int getConsoleLength() {
        return consoleLength;
    }

    public void setConsoleLength(int consoleLength) {
        if (consoleLength < CONSOLE_MIN_LENGTH) {
            return;
        }
        this.consoleLength = consoleLength;
    }

    public int getConsoleHeight() {
        return consoleHeight;
    }

    public void setConsoleHeight(int consoleHeight) {
        if (consoleHeight < CONSOLE_MIN_HEIGHT) {
            return;
        }
        this.consoleHeight = consoleHeight;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    public void setSoundEffectsVolume(float soundEffectsVolume) {
        this.soundEffectsVolume = soundEffectsVolume;
    }
}
