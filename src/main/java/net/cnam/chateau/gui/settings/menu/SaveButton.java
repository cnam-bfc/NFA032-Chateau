package net.cnam.chateau.gui.settings.menu;

import java.io.File;
import java.io.IOException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.error.CErrorDialog;

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
        try {
            File file = new File(AppSettings.DEFAULT_FILE_PATH);
            settings.save(file);
        } catch (IOException ex) {
            console.show(new CErrorDialog(CErrorDialog.Type.WARNING, "Impossible de sauvegarder les paramètres"));
        }
    }
}
