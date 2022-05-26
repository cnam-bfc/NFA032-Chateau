package net.cnam.chateau.structure.block.trap;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;

import java.util.Random;

public class BadTrap extends Trap {

    private static final int MIN_DMG = 5;
    private static final int MAX_DMG = 16;
    private final int dmg;

    public BadTrap(Random random) {
        super("Piège");

        this.dmg = random.nextInt(MIN_DMG, MAX_DMG);
        String description ="";
        switch (dmg){
            case (5),(6) -> description = "Une ficelle vous faisant chuter";
            case (7),(8) -> description = "Des flechettes sortant des murs";
            case (9),(10) -> description = "Des pieux au sol";
            case (11),(12) -> description = "Un coup de planche dans la figure";
            case (13),(14) -> description = "Un jet de feu";
            case (15),(16) -> description = "Un petit sceau d'acide";
        }
        super.setDescription(description);
    }

    public int getDmg() {
        return dmg;
    }

    @Override
    public void useEffect(Player player) {
        this.setActivate(false);
        try {
            player.damage(dmg);
        } catch (EntityDeadException ignored) {
        }
    }


}
