package net.cnam.entity.enemy;

import net.cnam.entity.Characteristic;
import net.cnam.object.Location;

public class Wizard extends Enemy {

    public Wizard(Location location) {
        super(new Characteristic(100,50,10,10,10), null, location, "Arbadakarba");
    }

    @Override
    public String getCharacter() {
        return "W";
    }

}
