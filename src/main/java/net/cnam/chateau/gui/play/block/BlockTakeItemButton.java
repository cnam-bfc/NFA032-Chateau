package net.cnam.chateau.gui.play.block;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.decorative.DecorativeBlock;

public class BlockTakeItemButton extends CButton {

    private BlockMenu menu;
    private Player player;
    private DecorativeBlock block;

    public BlockTakeItemButton(BlockMenu menu, Player player, DecorativeBlock block){
        super("Prendre " + block.getName());

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
