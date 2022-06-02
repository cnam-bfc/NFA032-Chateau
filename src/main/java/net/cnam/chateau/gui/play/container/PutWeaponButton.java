package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class PutWeaponButton extends CButton {
    private final App app;
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public PutWeaponButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Déposer l'arme");

        this.app = app;
        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        block.setHiddenItem(player.getWeapon());
        player.setWeapon(null);
        /*TakeWeaponButton takeWeaponButton = new TakeWeaponButton(app, menu, player, block);
        takeWeaponButton.setSelected(true);
        menu.getButtons().replace(this, takeWeaponButton);*/
        menu.updateButtons();
    }

    public Player getPlayer() {
        return player;
    }
}
