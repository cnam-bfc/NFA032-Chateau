package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.utils.StringUtils;

public class ReplaceWeaponButton extends CButton {
    private final Player player;
    private final Entity enemy;

    public ReplaceWeaponButton(App app, Player player, Entity enemy) {
        super(app, "");

        this.player = player;
        this.enemy = enemy;

        update();
    }

    @Override
    public void execute() {
        Weapon playerWeapon = player.getWeapon();
        player.setWeapon(enemy.getWeapon());
        enemy.setWeapon(playerWeapon);

        update();
    }

    private void update() {
        String[] text = new String[]{
                "Remplacer " + player.getWeapon().getName(),
                "par " + enemy.getWeapon().getName()
        };
        this.setText(text);
        this.setHeight(text.length);
        this.setLength(StringUtils.getMaximumLength(text));
    }
}
