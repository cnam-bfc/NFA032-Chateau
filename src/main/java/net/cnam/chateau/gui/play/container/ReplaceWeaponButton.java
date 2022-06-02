package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.block.container.Container;

public class ReplaceWeaponButton extends CButton {
    private final Player player;
    private final Container block;

    public ReplaceWeaponButton(App app, Player player, Container block) {
        super(app, "Échanger les armes");

        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        Item item = player.getWeapon();
        player.setWeapon((Weapon) block.getHiddenItem());
        block.setHiddenItem(item);
    }

    public Player getPlayer() {
        return player;
    }

    public Container getBlock() {
        return block;
    }
}
