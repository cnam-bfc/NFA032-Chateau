package net.cnam.chateau.entity;

import net.cnam.chateau.utils.Couple;

import java.util.List;

public class Puzzle {
    private final String question;
    private final List<Couple<String, Boolean>> answers;

    /**
     * Constructeur
     * 
     * @param question String : la question de l'énigme
     * @param answers List de couple String,Boolean permettant de définir les différentes réponses et si elles sont corrects/incorrects
     */
    public Puzzle(String question, List<Couple<String, Boolean>> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<Couple<String, Boolean>> getAnswers() {
        return answers;
    }
}
