package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.block.container.Container;

public class ReplaceWeaponButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public ReplaceWeaponButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Remplacer " + player.getWeapon().getName() + " avec " + block.getHiddenItem().getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        Item item = player.getWeapon();
        player.setWeapon((Weapon)block.getHiddenItem());
        block.setHiddenItem(item);
        this.menu.stopDisplay();
    }
}
