package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.structure.block.door.LockedDoor;

public class DoorMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public DoorMenu(App app, Player player, LockedDoor door) {
        super(0, 0, "Porte bloquée");

        CChoices choices = new CChoices(app, 1);

        if (player.hasKey()) {
            choices.add(new UseObjectButton(app, player, door, this));
        }

        if (!door.hasTryDestroy()) {
            choices.add(new DestroyDoorButton(app, player, door, this));
        }

        choices.add(new LeaveButton(app, this));

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
