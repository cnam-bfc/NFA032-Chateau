package net.cnam.chateau.gui.settings.menu;

import java.io.File;
import java.io.IOException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.ErrorDialog;

public class SaveButton extends CButton {

    private final Console console;
    private final AppSettings settings;
    private final SettingsMenu settingsMenu;

    public SaveButton(Console console, AppSettings settings, SettingsMenu settingsMenu) {
        super("Sauvegarder");

        this.console = console;
        this.settings = settings;
        this.settingsMenu = settingsMenu;
    }

    @Override
    public void execute() {
        settingsMenu.stopDisplaying();
        File settingsfile = new File(AppSettings.DEFAULT_FILE_PATH);
        try {
            settings.save(settingsfile);
        } catch (IOException ex) {
            console.show(new ErrorDialog(ErrorDialog.Type.ERROR, "Impossible de sauvegarder les paramètres dans\n" + settingsfile.getAbsolutePath()));
        }
    }
}
