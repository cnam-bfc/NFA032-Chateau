package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.block.container.Container;

public class ContainerMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ContainerMenu(App app, Player player, Container block) {
        super(0, 0, block.getName());

        CChoices choices = new CChoices(app, 1);

        if (player.hasItem() && block.hasItem() && !(block.getHiddenItem() instanceof Weapon || block.getHiddenItem() instanceof Key)) {
            choices.add(new ReplaceItemButton(app, this, player, block));
        }

        if (player.hasWeapon() && block.hasItem() && (block.getHiddenItem() instanceof Weapon)) {
            choices.add(new ReplaceWeaponButton(app, this, player, block));
        }

        if (player.hasWeapon() && !block.hasItem()) {
            choices.add(new PutWeaponButton(app, this, player, block));
        }

        if (player.hasItem() && !block.hasItem()) {
            choices.add(new PutItemButton(app, this, player, block));
        }

        if (!player.hasItem() && block.hasItem() && !(block.getHiddenItem() instanceof Weapon || block.getHiddenItem() instanceof Key)) {
            choices.add(new TakeItemButton(app, this, player, block));
        }

        if (!player.hasWeapon() && block.hasItem() && (block.getHiddenItem() instanceof Weapon)) {
            choices.add(new TakeWeaponButton(app, this, player, block));
        }

        if (block.getHiddenItem() instanceof Key){
            choices.add(new TakeKeyButton(app, this, player, block));
        }

        choices.add(new LeaveContainerButton(app, this));

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
