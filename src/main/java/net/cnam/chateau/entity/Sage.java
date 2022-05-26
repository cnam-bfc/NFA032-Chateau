package net.cnam.chateau.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sage extends Entity {
    public static List<Sage> sages = new LinkedList<>();

    public static void initSages() {
        sages.add(new Sage("Dumbledore"));
        sages.add(new Sage("Merlin"));
        sages.add(new Sage("Kristoff"));
        sages.add(new Sage("Sage Gris"));
        sages.add(new Sage("Salomon"));

        // TODO Si le fichier csv existe :
        //Boucler dessus et ajouter les sages à la liste.
        //Sinon finir la méthode
    }

    private Puzzle puzzle;

    /**
     * Constructeur
     *
     * @param stage    L'étage où se situe le sage
     * @param location Coordonnées du sage
     * @param name     Le nom du sage
     */
    public Sage(String name) {
        super(null, null, name);

        affectAPuzzle();
    }

    public boolean hasPuzzle() {
        return this.puzzle != null;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void affectAPuzzle() {
        if (Puzzle.puzzles.isEmpty()) {
            this.puzzle = null;
        } else {
            this.puzzle = Puzzle.puzzles.remove(new Random().nextInt(0, Puzzle.puzzles.size()));
        }
    }

    @Override
    public String getCharacter() {
        return "S";
    }
}
