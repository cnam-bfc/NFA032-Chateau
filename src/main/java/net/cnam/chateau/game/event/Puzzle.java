package net.cnam.chateau.game.event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.cnam.chateau.utils.Couple;

public class Puzzle {

    public static List<Puzzle> puzzles = new LinkedList<>();

    public static void initPuzzles() {
        ArrayList<Couple<String, Boolean>> answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Ronde", true));
        answersInit.add(new Couple<>("Plate", false));
        answersInit.add(new Couple<>("Tout est une question de point de vu", false));
        puzzles.add(new Puzzle("La Terre est-elle ronde ou plate ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("42", true));
        answersInit.add(new Couple<>("98", false));
        answersInit.add(new Couple<>("11", false));
        puzzles.add(new Puzzle("Combien ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("3,141592", true));
        answersInit.add(new Couple<>("2,71828", false));
        answersInit.add(new Couple<>("1,435991", false));
        answersInit.add(new Couple<>("0,834626", false));
        puzzles.add(new Puzzle("Je suis la constante d'Archimède", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Cnam", true));
        answersInit.add(new Couple<>("Cman", false));
        answersInit.add(new Couple<>("Manc", false));
        answersInit.add(new Couple<>("Canm", false));
        puzzles.add(new Puzzle("Ou êtes vous ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Harry Potter", true));
        answersInit.add(new Couple<>("Hermione", false));
        answersInit.add(new Couple<>("Voldemort", false));
        answersInit.add(new Couple<>("Alban", false));
        puzzles.add(new Puzzle("Quel nom du héro principal dans Harry Potter", answersInit));

        //Faire code pour ajouter des énigmes dispos sur un CSV,
        // premier = question
        // deuxième = rep
        // next = fausses réponses min 1, max 4
    }

    private String question;
    private List<Couple<String, Boolean>> answers;

    public Puzzle(String question, List<Couple<String, Boolean>> answers) {
        this.question = question;
        this.answers = answers;
    }

    /**
     * Prends une énigme au hasard. Prends l'énigme dans la liste des énigmes et
     * la supprime de la liste.
     *
     * @return
     */
    public static Puzzle getAPuzzle() {
        if (puzzles.isEmpty()) {
            // TODO traiter le cas ou il n'y a plus d'énigme
        }
        return puzzles.remove(new Random().nextInt(0, puzzles.size()));
    }
    
    /**
     * Méthode pour vérifier si la réponse du joueur est juste.
     * 
     * @param answer Couple d'une réponse
     * @return un boolean
     */
    public boolean isRight(Couple<String, Boolean> answer){
        return answer.getElemTwo();
    }
}
