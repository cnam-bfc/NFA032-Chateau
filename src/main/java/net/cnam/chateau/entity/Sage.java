package net.cnam.chateau.entity;

import net.cnam.chateau.App;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sage extends Entity {
    public static List<Sage> sages = new LinkedList<>();

    public static void initSages(App app) {
        sages.add(new Sage(app, "Dumbledore"));
        sages.add(new Sage(app, "Merlin"));
        sages.add(new Sage(app, "Kristoff"));
        sages.add(new Sage(app, "Sage Gris"));
        sages.add(new Sage(app, "Salomon"));

        // TODO Si le fichier csv existe :
        //Boucler dessus et ajouter les sages à la liste.
        //Sinon finir la méthode
    }

    private Puzzle puzzle;

    /**
     * Constructeur
     *
     * @param app  L'application
     * @param name Le nom du sage
     */
    public Sage(App app, String name) {
        super(app, null, null, name);

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
