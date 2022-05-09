package net.cnam.entity.enemy;

import net.cnam.entity.Characteristic;
import net.cnam.object.Location;
import net.cnam.object.weapon.Weapon;

public class Spider extends Enemy {

    public Spider(Location location) {
        super(new Characteristic(100,50,10,10,10), null, location, "Maurice");
    }

    @Override
    public String getCharacter() {
        return "S";
    }

}
