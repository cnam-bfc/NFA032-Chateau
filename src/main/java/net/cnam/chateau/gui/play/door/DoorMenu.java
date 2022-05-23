package net.cnam.chateau.gui.play.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.door.DoorLocked;
import net.cnam.chateau.utils.array.ArrayUtils;

public class DoorMenu extends CFrame implements DisplayableComponent{
    
    private boolean display = true;

    public DoorMenu(Player player, DoorLocked door) {
        super(new CLabel("Porte bloquee") ,0 ,0);
        
        SelectableComponent[] selectableComponent = new SelectableComponent[0];
        
        if (player.haveItem()) {
            ArrayUtils.addOnBottomOfArray(selectableComponent, new UseObjectButton(player, door, this));
        }
        
        if (!door.hasTryDestroy()) {
            ArrayUtils.addOnBottomOfArray(selectableComponent, new DestroyDoorButton(player, door, this));
        }
        
        ArrayUtils.addOnBottomOfArray(selectableComponent, new LeaveButton(this));
        
        CChoices choices = new CChoices(selectableComponent,1);
        
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
    
    public void stopDisplay(){
        this.display = false;
    }
    
}
