package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;

public class TakeWeaponButton extends CButton {
    private final Player player;
    private final Entity enemy;
    private final LootMenu lootMenu;

    public TakeWeaponButton(App app, Player player, Entity enemy, LootMenu lootMenu) {
        super(app, "Prendre " + enemy.getWeapon().getName());

        this.player = player;
        this.enemy = enemy;
        this.lootMenu = lootMenu;
    }

    @Override
    public void execute() {
        player.setWeapon(enemy.getWeapon());
        enemy.setWeapon(null);
        lootMenu.getChoices().remove(this);
    }
}
