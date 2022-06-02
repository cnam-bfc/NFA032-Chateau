package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.block.container.Container;

public class TakeWeaponButton extends CButton {
    private final App app;
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public TakeWeaponButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Prendre l'arme");

        this.app = app;
        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        player.setWeapon((Weapon) block.getHiddenItem());
        block.setHiddenItem(null);
        PutWeaponButton putWeaponButton = new PutWeaponButton(app, menu, player, block);
        putWeaponButton.setSelected(true);
        menu.getButtons().replace(this, putWeaponButton);
    }

    public Container getBlock() {
        return block;
    }
}
