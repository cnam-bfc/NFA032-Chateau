package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class EnemyDoor extends Door {
    
    private App app;
    private Enemy enemy;

    public EnemyDoor(App app, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);

        this.app = app;
    }

    public EnemyDoor(App app, Enemy enemy, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);
        
        this.app = app;
        this.enemy = enemy;
    }
    
    

}
