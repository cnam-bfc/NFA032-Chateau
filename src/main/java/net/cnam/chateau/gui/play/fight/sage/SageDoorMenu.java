package net.cnam.chateau.gui.play.fight.sage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.structure.block.door.SageDoor;

public class SageDoorMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public SageDoorMenu(App app, Player player, SageDoor door) {
        super(0, 0, "Porte bloquée par un Sage");

        CChoices choices = new CChoices(app, 1);

        choices.add(new AttackButton(app, player, door, this));

        if (door.getSage().hasPuzzle()) {
            choices.add(new PuzzleButton(app, player, door, this));
        }

        choices.add(new LeaveButton(app, this));

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public void stopLoopingMode() {
        this.display = false;
    }
}
