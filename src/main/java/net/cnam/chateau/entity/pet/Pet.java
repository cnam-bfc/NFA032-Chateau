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

    public abstract void power();

    public abstract String scream();

    public boolean isFollowingPlayer() {
        return followPlayer;
    }

    public void setFollowingPlayer(boolean followPlayer) {
        this.followPlayer = followPlayer;
    }
}
