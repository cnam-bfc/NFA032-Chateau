package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.SettingsFileCorruptedException;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.dialog.DialogType;
import net.cnam.chateau.gui.dialog.ErrorDialog;

import java.io.File;
import java.io.IOException;

public class GeneratorConfigCancelButton extends QuitComponentButton {
    private final App app;
    private final GeneratorSettings generatorSettings;

    public GeneratorConfigCancelButton(App app, GeneratorSettings generatorSettings, DisplayableComponent component) {
        super(app, component, "Annuler");

        this.app = app;
        this.generatorSettings = generatorSettings;
    }

    @Override
    public void execute() {
        super.execute();

        File generatorSettingsFile = new File(GeneratorSettings.DEFAULT_FILE_PATH);
        if (generatorSettingsFile.exists()) {
            try {
                generatorSettings.load(generatorSettingsFile);
            } catch (IOException | SettingsFileCorruptedException e) {
                app.getConsole().show(new ErrorDialog(DialogType.ERROR, "Impossible de recharger les anciens paramètres\n \nErreur : " + e.getMessage()));
            }
        }
    }
}
