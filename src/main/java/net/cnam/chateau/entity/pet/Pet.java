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
    public Pet(App app, String name, int health, int strength, int accuracy, int speed) {
        super(app, null, null, name, health, strength, accuracy, speed);

        this.setRenderPriority(1);
    }

    /**
     * Retourne le caractère à afficher pour le familier.
     *
     * @return Le caractère "P" en bleu
     */
    @Override
    public String getCharacter() {
        return CColor.BLUE + "P" + CColor.BLUE.getForegroundReset();
    }

    /**
     * Redéfinition de la méthode permettant de tuer une entité.
     * Ici pour tuer le familier
     */
    @Override
    public void kill() {
        super.kill();

        if (player.getPet() == this) {
            player.setPet(null);
        }
    }

    /**
     * Méthode abstraite pour permettre aux familiers d'avoir un pouvoir
     */
    public abstract void power();

    /**
     * Méthode permettant de savoir si le familier possédé actuellement par le joueur le suit ou non.
     *  
     * @return un boolean, true = suit le joueur | false ne le suit pas 
     */
    public boolean isFollowingPlayer() {
        return follow;
    }

    /**
     * Méthode permettant de définir si le familier suit ou non le joueur.
     * 
     * @param followPlayer Boolean, true pour suivre, false pour ne pas suivre
     */
    public void setFollowingPlayer(boolean followPlayer) {
        this.follow = followPlayer;
    }

    /**
     *  Méthode permettant de définir le joueur à qui va appartenir le familier.
     * 
     * @param player le joueur à qui il appartient.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
