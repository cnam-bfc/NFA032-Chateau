package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.dialog.ErrorDialog;

import java.io.File;
import java.io.IOException;

public class SaveButton extends QuitComponentButton {
    private final App app;

    public SaveButton(App app, SettingsMenu settingsMenu) {
        super(app, settingsMenu, "Sauvegarder");

        this.app = app;
    }

    @Override
    public void execute() {
        super.execute();

        File settingsFile = new File(AppSettings.DEFAULT_FILE_PATH);
        try {
            app.getSettings().save(settingsFile);
        } catch (IOException ex) {
            app.getConsole().show(new ErrorDialog(ErrorDialog.Type.ERROR, "Impossible de sauvegarder les paramètres dans\n" + settingsFile.getAbsolutePath()));
        }
    }
}
