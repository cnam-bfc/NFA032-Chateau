package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.decorative.Bed;

public class BedUseButton extends CButton {

    private BedMenu menu;
    private Bed bed;

    public BedUseButton(BedMenu menu, Bed bed) {
        super("Utiliser le lit");
        
        this.menu = menu;
        this.bed = bed;
    }
    
    @Override
    public void execute() {
        bed.use();
        this.menu.stopDisplay();
    }  
}
