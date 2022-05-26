package net.cnam.chateau.entity;

import net.cnam.chateau.App;

public class Sage extends Entity {
    private final App app;
    private Puzzle puzzle;

    /**
     * Constructeur
     *
     * @param app    L'application
     * @param name   Le nom du sage
     * @param puzzle Le puzzle
     */
    public Sage(App app, String name, Puzzle puzzle) {
        super(app, null, null, name);

        this.app = app;
        this.puzzle = puzzle;
    }

    public boolean hasPuzzle() {
        return this.puzzle != null;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void affectRandomPuzzle() {
        if (app.getCurrentGame().hasPuzzles()) {
            this.puzzle = app.getCurrentGame().getRandomPuzzle();
        } else {
            this.puzzle = null;
        }
    }

    @Override
    public String getCharacter() {
        return "S";
    }
}
