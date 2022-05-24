package net.cnam.chateau.entity.pet;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'un pet
 */
public abstract class Pet extends Entity {

    public static List<Pet> listPet = new LinkedList<>();

    public static void createListPet() {
        listPet.add(new Babe());
        listPet.add(new ChatPotte());
        listPet.add(new Idefix());
        listPet.add(new PanPan());
        listPet.add(new PepeLoiseau());
    }

    private Player player;
    private boolean followPlayer = true;

    /**
     * Constructeur
     *
     * @param player Le joeur qui possède le pet
     * @param name Le nom du pet
     */
    public Pet(Player player, String name) {
        super(player.getStage(), new Location(player.getLocation().getX(), player.getLocation().getY()), name);

        this.player = player;
        this.setRenderPriority(1);
    }

    public Pet(String name) {
        super(null, null, name);

        this.player = null;
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

    public abstract String scream();

    public boolean isFollowingPlayer() {
        return followPlayer;
    }

    public void setFollowingPlayer(boolean followPlayer) {
        this.followPlayer = followPlayer;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static Pet getAPet(Random random) {
        if (!listPet.isEmpty()) {
            return listPet.remove(random.nextInt(0, listPet.size()));
        }
        return null;
    }
}
