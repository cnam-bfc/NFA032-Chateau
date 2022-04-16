package net.cnam.structure;

import net.cnam.structure.block.Block;

public class Stage {

    private final Room[] rooms;

    public Stage(Room[] rooms) {
        this.rooms = rooms;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Block getBlock(int x, int y) {
        for (Room room : rooms) {
            if (room.isInside(x, y)) {
                return room.getBlock()[x - room.getX1()][y - room.getY1()];
            }
        }

        return null;
    }
}
