package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.direction.Orientation;

public class PlayMenu extends CFrame implements DisplayableComponent {

    private boolean display = true;

    public PlayMenu(Console console, AppSettings settings, MainMenu mainMenu) {
        super(new CLabel("Créer une partie"), 0, 0);

        CChoices choices = new CChoices(new SelectableComponent[]{
            new CChoices(new SelectableComponent[]{
                new OkButton(console, settings, this, mainMenu),
                new BackButton(this)
            }, Orientation.HORIZONTAL, 10)
        }, 1);

        this.getContent().getContent().add(choices);
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
