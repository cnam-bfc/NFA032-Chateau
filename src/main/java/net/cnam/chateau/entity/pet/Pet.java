package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Classe d'un pet
 */
public abstract class Pet extends Entity {
    public static List<Pet> petList = new LinkedList<>();

    public static void createListPet() {
        petList.add(new Babe());
        petList.add(new ChatPotte());
        petList.add(new Idefix());
        petList.add(new Ouini());
        petList.add(new PanPan());
        petList.add(new PepeLoiseau());
    }

    public static Pet getAPet(Random random) {
        if (!petList.isEmpty()) {
            return petList.remove(random.nextInt(0, petList.size()));
        }
        return null;
    }

    private Player player;
    private boolean followPlayer = true;

    /**
     * Constructeur
     *
     * @param name Le nom du pet
     */
    public Pet(String name) {
        super(null, null, name);

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
        return followPlayer;
    }

    public void setFollowingPlayer(boolean followPlayer) {
        this.followPlayer = followPlayer;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
