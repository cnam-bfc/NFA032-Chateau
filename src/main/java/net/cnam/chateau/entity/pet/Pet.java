package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.Location;

abstract public class Pet extends LivingEntity {

    public Pet(Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
    }

    @Override
    public String getCharacter() {
        return CColor.BLUE + "P" + CColor.BLUE.getForegroundReset();
    }

    abstract void power();

    abstract String scream();

}
