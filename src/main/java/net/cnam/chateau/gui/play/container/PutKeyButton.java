package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Container;

public class PutKeyButton extends CButton {
    private final ContainerMenu menu;
    private final Player player;
    private final Container block;

    public PutKeyButton(App app, ContainerMenu menu, Player player, Container block) {
        super(app, "Déposer " + player.getKey().getName() + " dans " + block.getName());

        this.menu = menu;
        this.player = player;
        this.block = block;
    }

    @Override
    public void execute() {
        block.setHiddenItem(player.getKey());
        player.setKey(null);
        this.menu.stopDisplay();
    }
}
