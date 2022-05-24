package net.cnam.chateau.gui.play.block;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.decorative.DecorativeBlock;

public class BlockReplaceItemButton extends CButton {

    private BlockMenu menu;
    private Player player;
    private DecorativeBlock block;

    public BlockReplaceItemButton(BlockMenu menu, Player player, DecorativeBlock block){
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
