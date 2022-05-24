package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class PutItemButton extends CButton {

    private ContainerMenu menu;
    private Player player;
    private Container block;

    public PutItemButton(ContainerMenu menu, Player player, Container block) {
        super("Déposer " + player.getItem().getName() + " dans " + block.getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        block.setHiddenItem(player.getItem());
        player.setItem(null);
        this.menu.stopDisplay();
    }
}
