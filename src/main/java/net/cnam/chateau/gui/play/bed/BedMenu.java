/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.decorative.Bed;
import net.cnam.chateau.utils.array.ArrayUtils;

public class BedMenu extends CFrame implements DisplayableComponent{
    
    private boolean display = true;

    public BedMenu (Player player, Bed bed) {
        super(new CLabel("Porte bloquee") ,0 ,0);
        
        CChoices choices = new CChoices(new SelectableComponent[]{
            new BedUseButton(this, bed, player),
            new BedLeaveButton(this)
        },1);
        
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
