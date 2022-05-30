package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.entity.enemy.Mimic;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;

import java.util.Random;

public class TrappedChest extends Block implements BlockListener {
    private Enemy enemy;


    public TrappedChest(App app, Random random) {
        this.enemy = new Mimic(app, random);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (this.enemy != null) {
                Fight fight = enemy.fight(player);
                if (!fight.isOver()) {
                    event.setCanceled(true);
                } else {
                    this.enemy = null;
                }
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
        if (event.getEntity() instanceof Player player && this.enemy == null) {
            try {
                player.getStage().setBlock(player.getLocation(), null);
            } catch (CoordinatesOutOfBoundsException ignored) {
            }
        }
    }

    @Override
    public String getCharacter() {
        return "C";
    }
}
