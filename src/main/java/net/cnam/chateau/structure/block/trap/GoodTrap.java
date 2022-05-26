package net.cnam.chateau.structure.block.trap;

import net.cnam.chateau.entity.Player;

import java.util.Random;

public class GoodTrap extends Trap {

    private static final int MIN_HEALTH = 5;
    private static final int MAX_HEALTH = 16;

    private final int health;

    public GoodTrap(Random random) {
        super("Piège bénéfique");

        this.health = random.nextInt(MIN_HEALTH, MAX_HEALTH);
        String description = "";
        switch (health) {
            case (5), (6), (7) -> description = "des voix encourageantes";
            case (8), (9), (10) -> description = "une musique apaisante";
            case (11), (12), (13) -> description = "un courant d'air chaud relaxant";
            case (14), (15), (16) -> description = "une mélodie divine";
        }
        super.setDescription(description);
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void useEffect(Player player) {
        this.setActivate(false);
        player.heal(health);
    }
}
