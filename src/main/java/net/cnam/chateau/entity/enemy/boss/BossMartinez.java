package net.cnam.chateau.entity.enemy.boss;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class BossMartinez extends Boss {

    /**
     * Constructeur
     *
     * @param console  La console
     * @param stage    L'étage où il se situe
     * @param location Coordonnées où il se situe
     */
    public BossMartinez(Console console, Stage stage, Location location) {
        super(console, stage, location, "Martinez", 100, 100, 100, 100, 100);

        this.setWeapon(new Weapon(10, 10, 10, "Livre : \"Java pour les nuls\""));
    }

    @Override
    public String getCharacter() {
        return CColor.RED + "M" + CColor.RED.getForegroundReset();
    }
}
