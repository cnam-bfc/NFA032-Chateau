package net.cnam.chateau.gui.play.door.trap;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.structure.block.door.TrappedDoor;

public class TrappedDoorMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public TrappedDoorMenu(App app, Player player, TrappedDoor door) {
        super(0, 0, "Porte piégée !");

        CChoices choices = new CChoices(app, 1);
        choices.add(new AcceptTrapButton(app, this, door, player));
        choices.add(new TryToLeaveButton(app, this, door, player));

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
    public void stopLoopingMode() {
        this.display = false;
    }
}