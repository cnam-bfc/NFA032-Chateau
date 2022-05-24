package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class TakeItemButton extends CButton {

    private ContainerMenu menu;
    private Player player;
    private Container block;

    public TakeItemButton(ContainerMenu menu, Player player, Container block) {
        super("Prendre " + block.getHiddenItem().getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        player.setItem(block.getHiddenItem());
        block.setHiddenItem(null);
        this.menu.stopDisplay();
    }
}
