package net.cnam.chateau.structure.block.trap;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;

import java.util.Random;

public class BadTrap extends Trap {

    private static final int MIN_DMG = 5;
    private static final int MAX_DMG = 21;
    private final int dmg;

    public BadTrap(Random random) {
        super("Piège");

        this.dmg = random.nextInt(MIN_DMG, MAX_DMG);
        String description ="";
        switch (dmg){
            case (5),(6),(7) -> description = "une ficelle vous faisant chuter";
            case (8),(9),(10) -> description = "des flechettes sortant des murs";
            case (11),(12),(13) -> description = "des pieux au sol";
            case (14),(15),(16) -> description = "un coup de planche dans la figure";
            case (17),(18) -> description = "un jet de feu";
            case (19),(20) -> description = "un petit sceau d'acide";
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
