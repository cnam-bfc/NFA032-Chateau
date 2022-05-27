package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.structure.block.Cage;

public class CageMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public CageMenu(App app, Player player, Cage cage) {
        super(0, 0, "Cage");

        CChoices choices = new CChoices(app, 1);

        if (player.hasPet() && cage.hasPet()) {
            choices.add(new ReplacePetButton(app, this, player, cage));
        }

        if (player.hasPet() && !cage.hasPet()) {
            choices.add(new DropPetButton(app, this, player, cage));
        }

        if (!player.hasPet() && cage.hasPet()) {
            choices.add(new TakePetButton(app, this, player, cage));
        }

        choices.add(new LeaveCageButton(app, this));

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

    public void stopDisplay() {
        this.display = false;
    }
}
