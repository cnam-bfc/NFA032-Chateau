package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.CGauge;

public class ConfigureMusicVolumeGauge extends CGauge {

    public ConfigureMusicVolumeGauge() {
        super(AppSettings.CONSOLE_MIN_LENGTH - 10);
    }
}
