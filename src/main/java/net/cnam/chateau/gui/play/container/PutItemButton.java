package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class PutItemButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public PutItemButton(AppSettings settings, ContainerMenu menu, Player player, Container block) {
        super(settings, "Déposer " + player.getItem().getName() + " dans " + block.getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        block.setHiddenItem(player.getItem());
        player.setItem(null);
        this.menu.stopDisplay();
    }
}
