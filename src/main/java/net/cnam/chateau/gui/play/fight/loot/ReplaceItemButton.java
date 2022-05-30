package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.utils.StringUtils;

public class ReplaceItemButton extends CButton {
    private final Player player;
    private final Entity enemy;

    public ReplaceItemButton(App app, Player player, Entity enemy, LootMenu lootMenu) {
        super(app, "");

        this.player = player;
        this.enemy = enemy;

        update();
    }

    @Override
    public void execute() {
        Item playerItem = player.getItem();
        player.setItem(enemy.getItem());
        enemy.setItem(playerItem);

        update();
    }

    private void update() {
        String[] text = new String[]{
                "Remplacer " + player.getItem().getName(),
                "par " + enemy.getItem().getName()
        };
        this.setText(text);
        this.setHeight(text.length);
        this.setHeight(StringUtils.getMaximumLength(text));
    }
}
