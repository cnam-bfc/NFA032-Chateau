package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.Block;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la map.
 */
public class Cage extends Block implements BlockListener  {
    
    private Item hiddenItem;
    private final String name = "Cage";
    private Console console;

    public Cage(Item hiddenItem, Console console) {
        this.hiddenItem = hiddenItem;
        this.console = console;
    }
    
    @Override
    public String getCharacter() {
        return "P";
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
