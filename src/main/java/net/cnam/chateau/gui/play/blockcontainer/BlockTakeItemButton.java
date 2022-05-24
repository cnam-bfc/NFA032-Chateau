package net.cnam.chateau.gui.play.blockcontainer;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.BlockContainer;

public class BlockTakeItemButton extends CButton {

    private BlockMenu menu;
    private Player player;
    private BlockContainer block;

    public BlockTakeItemButton(BlockMenu menu, Player player, BlockContainer block){
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
