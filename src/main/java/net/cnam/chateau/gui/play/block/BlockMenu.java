package net.cnam.chateau.gui.play.block;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.decorative.DecorativeBlock;
import net.cnam.chateau.structure.block.door.DoorLocked;
import net.cnam.chateau.utils.array.ArrayUtils;

public class BlockMenu extends CFrame implements DisplayableComponent{
    
    private boolean display = true;

    public BlockMenu (Player player, DecorativeBlock block) {
        super(new CLabel("Porte bloquee") ,0 ,0);
        
        SelectableComponent[] selectableComponent = new SelectableComponent[0];
        
        if (player.haveItem() && block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new BlockReplaceItemButton(player, block, this));
        }
        
        if (player.haveItem() && !block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new BlockPutItemButton(player, block, this));
        }

        if (!player.haveItem() && block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new BlockTakeItemButton(player, block, this));
        }
        
        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new BlockLeaveButton(this));
        
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
