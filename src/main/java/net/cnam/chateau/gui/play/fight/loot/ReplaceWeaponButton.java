package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.weapon.Weapon;

public class ReplaceWeaponButton extends CButton {
    private final App app;
    private final Player player;
    private final Entity enemy;
    private final LootMenu lootMenu;

    public ReplaceWeaponButton(App app, Player player, Entity enemy, LootMenu lootMenu) {
        super(app, "Remplacer " + player.getWeapon().getName() + "\npar " + enemy.getWeapon().getName());

        this.app = app;
        this.player = player;
        this.enemy = enemy;
        this.lootMenu = lootMenu;
    }

    @Override
    public void execute() {
        Weapon playerWeapon = player.getWeapon();
        player.setWeapon(enemy.getWeapon());
        enemy.setWeapon(playerWeapon);
        ReplaceWeaponButton replaceWeaponButton = new ReplaceWeaponButton(app, player, enemy, lootMenu);
        lootMenu.getChoices().replace(this, replaceWeaponButton);
        lootMenu.getChoices().select(replaceWeaponButton);
    }
}
