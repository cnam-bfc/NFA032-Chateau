package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;

public class ReplaceItemButton extends CButton {
    private final App app;
    private final Player player;
    private final Entity enemy;
    private final LootMenu lootMenu;

    public ReplaceItemButton(App app, Player player, Entity enemy, LootMenu lootMenu) {
        super(app, "Remplacer " + player.getItem().getName() + "\npar " + enemy.getItem().getName());

        this.app = app;
        this.player = player;
        this.enemy = enemy;
        this.lootMenu = lootMenu;
    }

    @Override
    public void execute() {
        Item playerItem = player.getItem();
        player.setItem(enemy.getItem());
        enemy.setItem(playerItem);
        ReplaceItemButton replaceItemButton = new ReplaceItemButton(app, player, enemy, lootMenu);
        lootMenu.getChoices().replace(this, replaceItemButton);
        replaceItemButton.setSelected(true);
    }
}
