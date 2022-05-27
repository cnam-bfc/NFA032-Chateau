package net.cnam.chateau.audio;

public class AudioFile {
    private final String filePath;

    public AudioFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
