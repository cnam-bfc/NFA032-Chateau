package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.ErrorDialog;

import java.io.File;
import java.io.IOException;

public class SaveButton extends CButton {
    private final App app;
    private final SettingsMenu settingsMenu;

    public SaveButton(App app, SettingsMenu settingsMenu) {
        super(app, "Sauvegarder");

        this.app = app;
        this.settingsMenu = settingsMenu;
    }

    @Override
    public void execute() {
        settingsMenu.stopLoopingMode();
        File settingsFile = new File(AppSettings.DEFAULT_FILE_PATH);
        try {
            app.getSettings().save(settingsFile);
        } catch (IOException ex) {
            app.getConsole().show(new ErrorDialog(ErrorDialog.Type.ERROR, "Impossible de sauvegarder les paramètres dans\n" + settingsFile.getAbsolutePath()));
        }
    }
}
