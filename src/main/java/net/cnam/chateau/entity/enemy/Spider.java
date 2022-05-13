package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.utils.Location;

public class Spider extends Enemy {

    public Spider(Location location) {
        super(new Characteristic(100,50,10,10,10), null, "Maurice", location);
    }

    @Override
    public String getCharacter() {
        return "S";
    }

}
