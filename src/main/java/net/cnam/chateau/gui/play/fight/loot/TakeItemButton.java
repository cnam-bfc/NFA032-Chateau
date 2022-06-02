package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;

public class TakeItemButton extends CButton {
    private final Player player;
    private final Entity enemy;
    private final LootMenu lootMenu;

    public TakeItemButton(App app, Player player, Entity enemy, LootMenu lootMenu) {
        super(app, "Prendre l'objet");

        this.player = player;
        this.enemy = enemy;
        this.lootMenu = lootMenu;
    }

    @Override
    public void execute() {
        player.setItem(enemy.getItem());
        enemy.setItem(null);
        lootMenu.getButtons().remove(this);
        lootMenu.getLeaveButton().setSelected(true);
    }

    public Entity getEnemy() {
        return enemy;
    }
}
