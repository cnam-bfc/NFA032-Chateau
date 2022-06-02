package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;

public class ReplaceItemButton extends CButton {
    private final Player player;
    private final Entity enemy;

    public ReplaceItemButton(App app, Player player, Entity enemy) {
        super(app, "Échanger les objets");

        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        Item playerItem = player.getItem();
        player.setItem(enemy.getItem());
        enemy.setItem(playerItem);
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getEnemy() {
        return enemy;
    }
}
