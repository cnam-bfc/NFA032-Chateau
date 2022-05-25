package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class EnemyDoor extends Door {
    
    private final Console console;
    private Enemy enemy;

    public EnemyDoor(Console console, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);

        this.console = console;
    }

    public EnemyDoor(Console console, Enemy enemy, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);
        
        this.console = console;
        this.enemy = enemy;
    }
}
