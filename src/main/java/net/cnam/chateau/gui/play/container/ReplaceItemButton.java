package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.container.Container;

public class ReplaceItemButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public ReplaceItemButton(AppSettings settings, ContainerMenu menu, Player player, Container block) {
        super(settings, "Remplacer " + player.getItem().getName() + " avec " + block.getHiddenItem().getName());

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
