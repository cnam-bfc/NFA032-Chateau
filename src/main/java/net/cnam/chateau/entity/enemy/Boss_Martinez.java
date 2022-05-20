package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.utils.Location;

public class Boss_Martinez extends Enemy{

    public Boss_Martinez(Location location) {
        super(new Characteristic(100,100,100,100,100), null, "Martinez", location);
    }
    
    @Override
    public String getCharacter() {
        return CColor.RED + "M" + CColor.RED.getForegroundReset();
    }
    
}
