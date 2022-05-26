package net.cnam.chateau.gui.play.sagedoor;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.door.SageDoor;
import net.cnam.chateau.utils.array.ArrayUtils;

public class SageDoorMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public SageDoorMenu(App app, Player player, SageDoor door) {
        super(0, 0, "Porte bloquée par un Sage");

        SelectableComponent[] selectableComponent = {new AttackButton(app.getSettings(), player, door, this)};

        if (door.getSage().hasPuzzle()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new PuzzleButton(app, player, door, this));
        }

        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new LeaveButton(app.getSettings(), this));

        CChoices choices = new CChoices(app.getSettings(), selectableComponent, 1);

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
