package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.structure.block.Bed;

public class BedMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public BedMenu(App app, Player player, Bed bed) {
        super(0, 0, "Lit");

        CChoices choices = new CChoices(app, 1);
        choices.add(new UseBedButton(app, this, bed, player));
        choices.add(new QuitComponentButton(app, this, "Quitter"));

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
