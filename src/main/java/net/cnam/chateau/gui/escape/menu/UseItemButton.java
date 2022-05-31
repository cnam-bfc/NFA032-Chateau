package net.cnam.chateau.gui.escape.menu;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.item.consumable.ConsumableItemMenu;

import java.util.LinkedList;
import java.util.List;

public class UseItemButton extends CButton {
    private final App app;
    private final EscapeMenu escapeMenu;
    private final Player player;

    public UseItemButton(App app, EscapeMenu escapeMenu, Player player) {
        super(app, "Utiliser " + player.getItem().getName());

        this.app = app;
        this.escapeMenu = escapeMenu;
        this.player = player;
    }

    @Override
    public void execute() {
        List<Entity> entities = new LinkedList<>();
        entities.add(player);
        if (player.hasPet()) {
            entities.add(player.getPet());
        }
        app.getConsole().show(new ConsumableItemMenu(app, player.getItem(), entities));
        escapeMenu.stopDisplaying();
    }
}