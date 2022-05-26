package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.door.LockedDoor;
import net.cnam.chateau.utils.array.ArrayUtils;

public class DoorMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public DoorMenu(AppSettings settings, Player player, LockedDoor door) {
        super(0, 0, "Porte bloquée");

        SelectableComponent[] selectableComponent = new SelectableComponent[0];

        if (player.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new UseObjectButton(settings, player, door, this));
        }

        if (!door.hasTryDestroy()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new DestroyDoorButton(settings, player, door, this));
        }

        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new LeaveButton(settings, this));

        CChoices choices = new CChoices(settings, selectableComponent, 1);

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
