package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.array.ArrayUtils;

public class CageMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public CageMenu(App app, Player player, Cage cage) {
        super(0, 0, "Cage");

        SelectableComponent[] selectableComponent = new SelectableComponent[0];

        if (player.hasPet() && cage.hasPet()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new ReplacePetButton(app, this, player, cage));
        }

        if (player.hasPet() && !cage.hasPet()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new DropPetButton(app, this, player, cage));
        }

        if (!player.hasPet() && cage.hasPet()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new TakePetButton(app, this, player, cage));
        }

        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new LeaveCageButton(app, this));

        CChoices choices = new CChoices(app, selectableComponent, 1);

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
