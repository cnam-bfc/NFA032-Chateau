package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'un pet
 */
public abstract class Pet extends Entity {

    private boolean followPlayer = true;

    /**
     * Constructeur
     *
     * @param stage L'étage où se situe le pet
     * @param location Coordonnées du pet
     * @param name Le nom du pet
     */
    public Pet(Stage stage, Location location, String name) {
        super(stage, location, name);

        this.setRenderPriority(1);
    }

    /**
     * Retourne le caractère à afficher pour un joueur
     *
     * @return Le caractère
     */
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
