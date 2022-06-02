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
    public Sage(App app, String name, Puzzle puzzle, int health, int strength, int accuracy, int speed) {
        super(app, null, null, name, health, strength, accuracy, speed);

        this.app = app;
        this.puzzle = puzzle;
    }

    /**
     * Méthode permettant de vérifier si le Sage possède une énigme.
     *
     * @return un boolean, vrai s'il possède une énigme, sinon faux
     */
    public boolean hasPuzzle() {
        return this.puzzle != null;
    }

    /**
     * Getter permettant de récupérer l'énigme que possède un Sage.
     *
     * @return l'énigme du Sage.
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Méthode permettant au Sage de récupérer une énigme si la liste n'est pas vide.
     */
    public void affectRandomPuzzle() {
        if (app.getCurrentGame().hasPuzzles()) {
            this.puzzle = app.getCurrentGame().getRandomPuzzle();
        } else {
            this.puzzle = null;
        }
    }

    /**
     * Redéfinition de la méthode pour afficher l'entité sur la carte.
     *
     * @return un String : "S" pour représenter le Sage
     */
    @Override
    public String getCharacter() {
        return "S";
    }
}
