package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.ErrorDialog;

import java.io.File;
import java.io.IOException;

public class GeneratorConfigSaveButton extends CButton {
    private final App app;
    private final GeneratorSettings generatorSettings;
    private final GeneratorConfigMenu menu;

    public GeneratorConfigSaveButton(App app, GeneratorSettings generatorSettings, GeneratorConfigMenu menu) {
        super(app, "Sauvegarder");

        this.app = app;
        this.generatorSettings = generatorSettings;
        this.menu = menu;
    }

    @Override
    public void execute() {
        File generatorSettingsFile = new File(GeneratorSettings.DEFAULT_FILE_PATH);
        try {
            generatorSettings.save(generatorSettingsFile);
        } catch (IOException e) {
            app.getConsole().show(new ErrorDialog(ErrorDialog.Type.ERROR, "Impossible de sauvegarder les paramètres\n \nErreur : " + e.getMessage()));
        }
        menu.stopLoopingMode();
    }
}
