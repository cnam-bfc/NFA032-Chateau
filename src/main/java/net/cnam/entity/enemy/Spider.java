package net.cnam.entity.enemy;

import net.cnam.entity.Characteristic;
import net.cnam.object.Location;

public class Spider extends Enemy {

    public Spider(Location location) {
        super(new Characteristic(100,50,10,10,10), null, "Maurice", location);
    }

    @Override
    public String getCharacter() {
        return "S";
    }

}
