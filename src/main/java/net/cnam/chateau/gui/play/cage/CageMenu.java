package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.container.Cage;
import net.cnam.chateau.utils.array.ArrayUtils;

public class CageMenu extends CFrame implements DisplayableComponent{
    
    private boolean display = true;

    public CageMenu (Player player, Cage cage) {
        super(new CLabel("Cage") ,0 ,0);
        
        SelectableComponent[] selectableComponent = new SelectableComponent[0];
        
        if (player.hasPet() && cage.hasPet()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new ReplacePetButton(this, player, cage));
        }
        
        if (player.hasPet() && !cage.hasPet()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new DropPetButton(this, player, cage));
        }

        if (!player.hasPet() && cage.hasPet()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new TakePetButton(this, player, cage));
        }
        
        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new CageLeaveButton(this));
        
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