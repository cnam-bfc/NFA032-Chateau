package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Characteristic;
import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.Location;

abstract public class Pet extends LivingEntity {
    
    private boolean followPlayer = true;

    public Pet(Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
    }

    @Override
    public String getCharacter() {
        return CColor.BLUE + "P" + CColor.BLUE.getForegroundReset();
    }
    
    public void follow(Location location){
        this.getLocation().setX(location.getX());
        this.getLocation().setY(location.getY());
    }

    abstract void power();

    abstract String scream();

    public boolean isFollowPlayer() {
        return followPlayer;
    }

    public void setFollowPlayer(boolean followPlayer) {
        this.followPlayer = followPlayer;
    }

}
