package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.structure.block.container.Container;

public class ReplaceKeyButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public ReplaceKeyButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Remplacer " + player.getKey().getName() + " par " + block.getHiddenItem().getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        Key key = player.getKey();
        player.setKey((Key)block.getHiddenItem());
        block.setHiddenItem(key);
        this.menu.stopDisplay();
    }
}
