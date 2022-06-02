package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.weapon.Weapon;

public class ReplaceWeaponButton extends CButton {
    private final Player player;
    private final Entity enemy;

    public ReplaceWeaponButton(App app, Player player, Entity enemy) {
        super(app, "Échanger les armes");

        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        Weapon playerWeapon = player.getWeapon();
        player.setWeapon(enemy.getWeapon());
        enemy.setWeapon(playerWeapon);
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getEnemy() {
        return enemy;
    }
}
