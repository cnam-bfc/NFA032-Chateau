package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.blockcontainer.BlockMenu;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.Block;

public abstract class BlockContainer extends Block implements BlockListener {

    private Item hiddenItem;
    private final String name;
    private Console console;

    public BlockContainer(Console console, String name ,Item hiddenItem) {
        this.console = console;
        this.name = name;
        this.hiddenItem = hiddenItem;
    }

    public boolean hasItem() {
        return this.hiddenItem != null;
    }

    public Item getHiddenItem() {
        return hiddenItem;
    }

    public void setHiddenItem(Item hiddenItem) {
        this.hiddenItem = hiddenItem;
    }

    public String getName(){
        return this.name;
    }
    
    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() instanceof Player player && (this.hasItem() || player.haveItem())) {
            console.show(new BlockMenu(player, this));
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}