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
            case (5),(6) -> description = "une ficelle vous faisant chuter";
            case (7),(8) -> description = "des flechettes sortant des murs";
            case (9),(10) -> description = "des pieux au sol";
            case (11),(12) -> description = "un coup de planche dans la figure";
            case (13),(14) -> description = "un jet de feu";
            case (15),(16) -> description = "un petit sceau d'acide";
        }
        super.setDescription(description);
    }

    public int getDmg() {
        return dmg;
    }

    @Override
    public void useEffect(Player player) {
        try {
            player.damage(dmg);
        } catch (EntityDeadException ignored) {
        }
    }


}
