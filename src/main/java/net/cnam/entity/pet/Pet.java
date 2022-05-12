package net.cnam.entity.pet;

import net.cnam.entity.Characteristic;
import net.cnam.entity.LivingEntity;
import net.cnam.utils.Location;

abstract public class Pet extends LivingEntity {

    public Pet(Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
    }

    @Override
    public String getCharacter() {
        return "\u001b[44m" + "P" + "\u001b[44m";
    }
    
    abstract void power();
    
    abstract String scream();

}
