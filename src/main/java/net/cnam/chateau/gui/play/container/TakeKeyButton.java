package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.structure.block.container.Container;

public class TakeKeyButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public TakeKeyButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Prendre " + block.getHiddenItem().getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        player.addKey((Key)block.getHiddenItem());
        block.setHiddenItem(null);
        this.menu.stopDisplay();
    }
}
