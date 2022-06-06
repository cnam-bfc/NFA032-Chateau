package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.structure.block.door.LockedDoor;

public class DoorMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public DoorMenu(App app, Player player, LockedDoor door) {
        super(0, 0, "Porte bloquée");

        CChoices choices = new CChoices(app, 1);

        if (player.hasKey(door.getKey())) {
            choices.add(new OpenDoorButton(app, door, this));
        } else {
            getContentPane().getComponents().add(new CLabel("Vous n'avez pas la bonne clé, cherchez la !"));
        }

        if (!door.hasTryDestroy()) {
            choices.add(new DestroyDoorButton(app, player, door, this));
        }

        choices.add(new QuitComponentButton(app, this, "Partir"));

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
