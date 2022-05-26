package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.structure.block.container.Container;
import net.cnam.chateau.utils.array.ArrayUtils;

public class ContainerMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ContainerMenu(AppSettings settings, Player player, Container block) {
        super(0, 0, block.getName());

        SelectableComponent[] selectableComponent = new SelectableComponent[0];

        if (player.hasItem() && block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new ReplaceItemButton(settings, this, player, block));
        }

        if (player.hasItem() && !block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new PutItemButton(settings, this, player, block));
        }

        if (!player.hasItem() && block.hasItem()) {
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent,
                    new TakeItemButton(settings, this, player, block));
        }

        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new LeaveContainerButton(settings, this));

        CChoices choices = new CChoices(settings, selectableComponent, 1);

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
