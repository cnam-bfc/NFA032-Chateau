package net.cnam.chateau.audio;

public enum SoundEffect {
    HOVER("/sound/button_hover.wav"),
    SELECT("/sound/Undertale - select.wav");

    private final String filePath;

    private SoundEffect(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
