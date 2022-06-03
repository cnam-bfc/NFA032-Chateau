package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.information.commands.CommandsMenu;
import net.cnam.chateau.gui.information.credits.CreditsMenu;
import net.cnam.chateau.gui.information.entities.EntitiesMenu;
import net.cnam.chateau.gui.information.structure.SpecialBlockMenu;

public class InfoMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public InfoMenu(App app) {
        super(0, 0, "Informations");

        CChoices choices = new CChoices(app, 1);

        choices.add(new OpenComponentButton(app, new EntitiesMenu(app), "Afficher les entités"));
        choices.add(new OpenComponentButton(app, new SpecialBlockMenu(app), "Afficher les structures"));
        choices.add(new OpenComponentButton(app, new CommandsMenu(), "Afficher les commandes"));
        choices.add(new OpenComponentButton(app, new CreditsMenu(), "Crédits"));
        choices.add(new QuitComponentButton(app, this, "Retour"));

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if (event.getKey() == 27) {
            stopLoopingMode();
            return;
        }

        super.onKeyPressed(event);
    }

    public void stopLoopingMode() {
        display = false;
    }
}

