package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.trap.Trap;

public class TrappedDoor extends Door {

    Trap trap;
    public TrappedDoor(Stage stage, Room roomOne, Room roomTwo) {
        super(stage, roomOne, roomTwo);
    }
}
