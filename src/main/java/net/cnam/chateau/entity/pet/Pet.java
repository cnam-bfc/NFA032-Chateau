package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;

/**
 * Classe d'un pet
 */
public abstract class Pet extends Entity {
    private Player player;
    private boolean follow = true;

    /**
     * Constructeur
     *
     * @param app  L'application
     * @param name Le nom du pet
     */
    public Pet(App app, String name) {
        super(app, null, null, name);

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

    @Override
    public void kill() {
        super.kill();

        if (player.getPet() == this) {
            player.setPet(null);
        }
    }

    public abstract void power();

    public boolean isFollowingPlayer() {
        return follow;
    }

    public void setFollowingPlayer(boolean followPlayer) {
        this.follow = followPlayer;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
