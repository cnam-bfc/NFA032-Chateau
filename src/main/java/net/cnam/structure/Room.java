package net.cnam.structure;

import net.cnam.object.Location;
import net.cnam.structure.block.Block;

public class Room {

    private final Location origin;
    private final Block[][] block;

    public Room(Location origin, Block[][] block) {
        this.origin = origin;
        this.block = block;
    }

    public Location getOrigin() {
        return origin;
    }

    public Block[][] getBlock() {
        return block;
    }

    public int getX1() {
        return origin.getX();
    }

    public int getY1() {
        return origin.getY();
    }

    public int getX2() {
        return origin.getX() + block.length;
    }

    public int getY2() {
        return origin.getY() + block[0].length;
    }

    public boolean isInside(int x, int y) {
        return x > this.getX1() && x < this.getX2() && y > this.getY1() && y < this.getY2();
    }
}
