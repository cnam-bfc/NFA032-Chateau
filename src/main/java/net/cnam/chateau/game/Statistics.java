package net.cnam.chateau.game;

public class Statistics {

    private long seed;
    private String nom;
    private boolean bossDefeated;
    private int nbEnemyKilled;
    private int nbRoomsVisited;
    private int score;

    private boolean cheatModeActivate;

    public Statistics(long seed, String nom) {
        this.nom = nom;
        this.seed = seed;
    }
}
