package net.cnam.chateau;

import java.io.File;

public class SettingsFileCorruptedException extends Exception {
    private final File file;

    public SettingsFileCorruptedException(File file, String message) {
        super(message);

        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
