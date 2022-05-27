package net.cnam.chateau.audio;

public enum Music {
    MENU("Stranger Things 3 - The Game Soundtrack - Hess Farm.wav"),
    GAME("Stranger Things 3 - The Game Soundtrack - Russian Farm Base.wav"),
    FIGHT("Final Fantasy 10 - Battle Theme.wav"),
    DEATH("Risitas, meme Original.wav"),
    ERROR("Curb your enthusiasm - Credits + Theme Song.wav");

    private static final String basePath = "/songs/";
    private final String fileName;

    Music(String fileName) {
        this.fileName = fileName;
    }

    public AudioFile getAudioFile() {
        return new AudioFile(basePath + fileName);
    }
}
