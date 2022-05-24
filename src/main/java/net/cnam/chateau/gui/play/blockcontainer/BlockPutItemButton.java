package net.cnam.chateau.gui.play.blockcontainer;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.BlockContainer;
import net.cnam.chateau.structure.block.decorative.DecorativeBlock;

public class BlockPutItemButton extends CButton {

    private BlockMenu menu;
    private Player player;
    private BlockContainer block;

    public BlockPutItemButton(BlockMenu menu, Player player, BlockContainer block){
        super("Deposer " + player.getItem().getName() + " dans " + block.getName());

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
