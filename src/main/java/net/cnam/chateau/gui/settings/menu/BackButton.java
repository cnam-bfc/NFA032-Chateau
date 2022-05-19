package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.gui.component.CButton;

public class BackButton extends CButton {

    private final SettingsMenu settingsMenu;

    public BackButton(SettingsMenu settingsMenu) {
        super("Retour");

        this.settingsMenu = settingsMenu;
    }

    @Override
    public void execute() {
        settingsMenu.stopDisplaying();
    }
}
