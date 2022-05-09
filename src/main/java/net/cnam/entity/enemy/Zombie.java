package net.cnam.entity.enemy;

import net.cnam.entity.Characteristic;
import net.cnam.object.Location;

public class Zombie extends Enemy {

    public Zombie(Location location) {
        super(new Characteristic(100,50,10,10,10), null, location, "Twilette");
    }

    @Override
    public String getCharacter() {
        return "Z";
    }

}
