package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.decorative.Bed;

public class BedMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public BedMenu(Player player, Bed bed) {
        super(0, 0, "Lit");

        CChoices choices = new CChoices(new SelectableComponent[]{
                new UseBedButton(this, bed, player),
                new LeaveBedButton(this)
        }, 1);

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
