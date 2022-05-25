package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class EnemyDoor extends Door {
    
    private Console console;
    private Enemy enemy;
    private boolean visited = false;

    public EnemyDoor(Console console, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);

        this.console = console;
    }

    public EnemyDoor(Console console, Enemy enemy, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);
        
        this.console = console;
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (this.enemy != null) {
            event.setCanceled(true);
        }
    }

    @Override
    public String getCharacter() {
        if (!visited || this.enemy == null) {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        } else {
            return CColor.RED + enemy.getCharacter() + CColor.RED.getForegroundReset();
        }
    }
}
