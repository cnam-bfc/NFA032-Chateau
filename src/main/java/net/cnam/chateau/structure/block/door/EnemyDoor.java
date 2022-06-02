package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class EnemyDoor extends Door {
    private Enemy enemy;
    private boolean visited = false;

    public EnemyDoor(Stage stage, Room roomOne, Room roomTwo, Enemy enemy) {
        super(stage, roomOne, roomTwo);

        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public boolean isLocked() {
        return enemy != null;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        this.visited = true;
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
        super.onEntityEnterBlock(event);
    }

    @Override
    public String getCharacter() {
        if (!visited || this.enemy == null) {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        } else {
            return CColor.BRIGHT_RED + enemy.getCharacter() + CColor.BRIGHT_RED.getForegroundReset();
        }
    }
}
