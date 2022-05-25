package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.container.Container;
import net.cnam.chateau.utils.array.ArrayUtils;

public class ContainerMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ContainerMenu(Player player, Container block) {
        super(0, 0, block.getName());

        SelectableComponent[] selectableComponent = new SelectableComponent[0];

        if (player.haveItem() && block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new ReplaceItemButton(this, player, block));
        }

        if (player.haveItem() && !block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new PutItemButton(this, player, block));
        }

        if (!player.haveItem() && block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new TakeItemButton(this, player, block));
        }

        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new LeaveContainerButton(this));

        CChoices choices = new CChoices(selectableComponent, 1);

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
