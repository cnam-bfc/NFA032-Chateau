package net.cnam.chateau.gui.play.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.main.menu.MainMenu;
import net.cnam.chateau.utils.direction.Orientation;

import java.util.Random;

public class PlayMenu extends CFrame implements DisplayableComponent {

    private boolean display = true;

    public PlayMenu(App app, MainMenu mainMenu) {
        super(new CLabel("Créer une partie"), 0, 0);

        CTextField seedTextField = new CTextField(new Random().nextLong() + "", AppSettings.CONSOLE_MIN_LENGTH - 10);
        CButton okButton = new OkButton(app, this, mainMenu, seedTextField);
        CButton backButton = new BackButton(this);
        CChoices actions = new CChoices(new SelectableComponent[]{
                okButton,
                backButton
        }, Orientation.HORIZONTAL, 10);

        CChoices choices = new CChoices(new SelectableComponent[]{
                seedTextField,
                actions
        }, 1);
        choices.select(actions);

        this.getContentPane().getComponents().add(choices);
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
