package net.cnam.chateau.gui.play.blockcontainer;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.container.BlockContainer;

public class BlockReplaceItemButton extends CButton {

    private BlockMenu menu;
    private Player player;
    private BlockContainer block;

    public BlockReplaceItemButton(BlockMenu menu, Player player, BlockContainer block){
        super("Remplacer " + player.getItem().getName() + " avec " + block.getHiddenItem().getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        Item item = block.getHiddenItem();
        block.setHiddenItem(player.getItem());
        player.setItem(item);
        this.menu.stopDisplay();
    }
}
