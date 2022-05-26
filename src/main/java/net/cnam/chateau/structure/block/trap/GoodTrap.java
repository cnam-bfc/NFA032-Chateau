package net.cnam.chateau.structure.block.trap;

import net.cnam.chateau.entity.Player;

import java.util.Random;

public class GoodTrap extends Trap{

    private final int MIN_HEALTH = 5;
    private final int MAX_HEALTH = 16;

    private final int health;

    public GoodTrap(Random random) {
        super("Piège bénéfique");

        this.health = random.nextInt(MIN_HEALTH, MAX_HEALTH);
        String description ="";
        switch (health){
            case (5),(6),(7) -> description = "Des voix encourageantes";
            case (8),(9),(10) -> description = "Une musique apaisante";
            case (11),(12),(13) -> description = "Un courant d'air chaud relaxant";
            case (14),(15),(16) -> description = "Une mélodie divine";
        }
        super.setDescription(description);
    }

    @Override
    public void useEffect(Player player) {
            player.heal(health);
    }
}
