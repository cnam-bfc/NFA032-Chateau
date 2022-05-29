package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.container.ContainerMenu;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.Block;

public abstract class Container extends Block implements BlockListener {

    private final App app;
    private final String name;
    private Item hiddenItem;
    private boolean opened = false;

    public Container(App app, String name, Item hiddenItem) {
        this.app = app;
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

    public String getName() {
        return this.name;
    }

    public String getCharacter(String string) {
        if (this.opened) {
            if (this.hasItem()) {
                return CColor.BRIGHT_RED + string + CColor.BRIGHT_RED.getForegroundReset();
            } else {
                return CColor.GREEN + string + CColor.GREEN.getForegroundReset();
            }
        }
        return string;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        this.opened = true;
        if (event.getEntity() instanceof Player player && (this.hasItem() || player.hasItem() || player.hasKey() || player.hasWeapon())) {
            app.getConsole().show(new ContainerMenu(app, player, this));
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}