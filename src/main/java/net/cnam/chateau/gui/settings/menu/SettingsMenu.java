package net.cnam.chateau.gui.settings.menu;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoises;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;

public class SettingsMenu extends CFrame implements DisplayableComponent {

    private final CChoises buttonsChoices;
    private boolean display = true;

    public SettingsMenu(Console console) {
        super(new CLabel("Paramètres"), 0, 0);

        this.buttonsChoices = new CChoises(new SelectableComponent[]{
            new ConfigureScreenButton(console),
            new ConfigureMusicVolumeGauge(),
            new BackButton(this),
            new DebugGeneratorButton(),
            new DebugKeysButton()
        }, 1);

        this.getContent().getContent().add(buttonsChoices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}
