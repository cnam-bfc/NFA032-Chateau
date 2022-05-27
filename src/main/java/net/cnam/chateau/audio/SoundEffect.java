package net.cnam.chateau.audio;

public enum SoundEffect {
    HOVER("button_hover.wav"),
    SELECT("Undertale - select.wav");

    private static final String basePath = "/assets/sound/";
    private final String fileName;

    SoundEffect(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return basePath + fileName;
    }
}
