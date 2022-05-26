package net.cnam.chateau.entity;

import net.cnam.chateau.utils.Couple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Puzzle {
    public static List<Puzzle> puzzles = new LinkedList<>();

    static {
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
        puzzles.add(new Puzzle("Je suis la constante d'Archimède, qui suis-je ?", answersInit));

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

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("5 fruits et légumes par jour", true));
        answersInit.add(new Couple<>("ses morts", false));
        answersInit.add(new Couple<>("3 fruits et légumes par jour", false));
        answersInit.add(new Couple<>("Le fiak de victor", false));
        puzzles.add(new Puzzle("il faut manger", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Une casserole avec de l'eau", true));
        answersInit.add(new Couple<>("Un casserole avec du beurre", false));
        answersInit.add(new Couple<>("Une poêle", false));
        answersInit.add(new Couple<>("Une chaussure", false));
        puzzles.add(new Puzzle("Dans quoi cuit-on les pâtes ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("7", true));
        answersInit.add(new Couple<>("10", false));
        answersInit.add(new Couple<>("5", false));
        answersInit.add(new Couple<>("3", false));
        puzzles.add(new Puzzle("Combien de coup de fouet pour dresser Victor", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("VS code", true));
        answersInit.add(new Couple<>("NetBeans", false));
        answersInit.add(new Couple<>("Intellij", false));
        puzzles.add(new Puzzle("Le pire IDE : ", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("9", true));
        answersInit.add(new Couple<>("12", false));
        puzzles.add(new Puzzle("1+2(4)", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Ismail/Melvin/Célian", true));
        answersInit.add(new Couple<>("Melvin/Célian/Ismail", false));
        answersInit.add(new Couple<>("Célian/Melvin/Ismail", false));
        puzzles.add(new Puzzle("Dans quel ordre sont parties les candidats de Cnam-Lanta", answersInit));

        // TODO Faire code pour ajouter des énigmes dispos sur un CSV,
        // premier = question
        // deuxième = rep
        // next = fausses réponses min 1, max 4
    }

    private final String question;
    private final List<Couple<String, Boolean>> answers;

    public Puzzle(String question, List<Couple<String, Boolean>> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    /**
     * Prends une énigme au hasard. Prends l'énigme dans la liste des énigmes et
     * la supprime de la liste.
     *
     * @return un puzzle
     */



    public List<Couple<String, Boolean>> getAnswers() {
        return answers;
    }

    /**
     * Méthode pour vérifier si la réponse du joueur est juste.
     *
     * @param answer Couple d'une réponse
     * @return un boolean
     */


    public boolean isRight(Couple<String, Boolean> answer) {
        return answer.getElemTwo();
    }
}
