package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.container.Container;

public class ReplaceItemButton extends CButton {
    private final Player player;
    private final Container block;

    public ReplaceItemButton(App app, Player player, Container block) {
        super(app, "Échanger les objets");

        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        Item item = player.getItem();
        player.setItem(block.getHiddenItem());
        block.setHiddenItem(item);
    }

    public Player getPlayer() {
        return player;
    }

    public Container getBlock() {
        return block;
    }
}
