package net.cnam.generator;

public class CommonWallGenerator {

    private int startSegment;
    private int endSegment;
    private boolean horizontal; // True = horizontal | False = vertical
    private int positionSegment; // si segment horizontal = y / si segment vertical = x 
    private int length;

    public CommonWallGenerator(int startSegment, int endSegment, boolean orientation, int positionSegment) {
        this.startSegment = startSegment;
        this.endSegment = endSegment;
        this.horizontal = orientation;
        this.positionSegment = positionSegment;
        this.length = endSegment - startSegment;
    }

    public int getStartSegment() {
        return startSegment;
    }

    public int getEndSegment() {
        return endSegment;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getPositionSegment() {
        return positionSegment;
    }

    public int getLength() {
        return length;
    }

    public boolean isBreakable (CommonWallGenerator segmentTwo) {

        if (this.horizontal != segmentTwo.isHorizontal()) {
            return false;
        }
        if (this.positionSegment != segmentTwo.getPositionSegment()) {
            return false;
        }
        if (segmentTwo.getStartSegment() >= this.endSegment || this.startSegment >= segmentTwo.getEndSegment()) {
            return false;
        }
        if (this.startSegment < segmentTwo.getStartSegment()) {
            if (this.length + segmentTwo.getLength() - this.startSegment + segmentTwo.getEndSegment() >= 3) {
                return true;
            } else {
                return false;
            }
        } else if (this.length + segmentTwo.getLength() - segmentTwo.getStartSegment() + this.endSegment >= 3) {
            return true;
        } else {
            return false;
        }

    }
    
    public GeneratorWall createWall ()

}
