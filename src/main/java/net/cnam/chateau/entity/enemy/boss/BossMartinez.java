package net.cnam.chateau.entity.enemy.boss;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.weapon.Weapon;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class BossMartinez extends Boss {
    /**
     * Constructeur
     *
     * @param app      L'application
     * @param stage    L'étage où il se situe
     * @param location Coordonnées où il se situe
     */
    public BossMartinez(App app, Stage stage, Location location) {
        super(app, stage, location, "Martinez", 100, 100, 100, 100, 100);

        this.setWeapon(new Weapon("Livre : \"Java pour les nuls\"", "L'arme divine pour apprendre à coder", 10, 10, 10));
    }

    @Override
    public String getCharacter() {
        return CColor.RED + "M" + CColor.RED.getForegroundReset();
    }
}
