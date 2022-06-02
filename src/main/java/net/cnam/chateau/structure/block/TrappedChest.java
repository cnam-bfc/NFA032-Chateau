package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.entity.enemy.Mimic;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;

import java.util.Random;

public class TrappedChest extends Block implements PlayerInteractListener {
    private Enemy enemy;

    public TrappedChest(App app, Random random) {
        super("Coffre");

        this.enemy = new Mimic(app, random);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (this.enemy != null) {
            Fight fight = enemy.fight(player, false);
            if (fight.isOver()) {
                this.enemy = null;
                try {
                    player.getStage().setBlock(player.getLocation(), null);
                } catch (CoordinatesOutOfBoundsException ignored) {
                }
            }
        }
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
