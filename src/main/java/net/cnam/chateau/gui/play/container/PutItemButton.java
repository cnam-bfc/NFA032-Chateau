package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class PutItemButton extends CButton {
    private final App app;
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public PutItemButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Déposer l'objet");

        this.app = app;
        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        block.setHiddenItem(player.getItem());
        player.setItem(null);
        TakeItemButton takeItemButton = new TakeItemButton(app, menu, player, block);
        takeItemButton.setSelected(true);
        menu.getButtons().replace(this, takeItemButton);
    }

    public Player getPlayer() {
        return player;
    }
}
