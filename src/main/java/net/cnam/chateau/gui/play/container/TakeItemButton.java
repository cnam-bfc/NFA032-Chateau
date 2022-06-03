package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class TakeItemButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public TakeItemButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Prendre l'objet");

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        player.setItem(block.getHiddenItem());
        block.setHiddenItem(null);
        /* PutItemButton putItemButton = new PutItemButton(app, menu, player, block);
        putItemButton.setSelected(true);
        menu.getButtons().replace(this, putItemButton);*/
        menu.updateButtons();
    }

    public Container getBlock() {
        return block;
    }
}
