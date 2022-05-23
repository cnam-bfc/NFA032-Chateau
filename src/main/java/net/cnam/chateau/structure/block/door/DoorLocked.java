package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.game.event.Key;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.door.DoorMenu;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class DoorLocked extends Door {

    private boolean lock = true;
    private boolean tryDestroyDoor = false;
    private Key key;
    private Console console;

    public DoorLocked(Console console, Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);

        this.console = console;
    }

    public DoorLocked(Console console, Stage stage, Room roomOne, Room roomTwo, Key key) {
        super(stage, roomOne, roomTwo);

        this.console = console;
        this.key = key;
    }
    
    public boolean hasTryDestroy() {
        return tryDestroyDoor;
    }

    public boolean tryDestroy() {
        this.tryDestroyDoor = true;
        return (Math.random() * 100 > 75);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
    
    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public boolean isLocked() {
        return lock;
    }

    @Override
    public String getCharacter() {
        if (isLocked()) {
            return CColor.RED + "D" + CColor.RED.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() instanceof Player player && this.lock) {
            console.show(new DoorMenu(player, this));
        }

        super.onEntityEnterBlock(event);
    }
}
