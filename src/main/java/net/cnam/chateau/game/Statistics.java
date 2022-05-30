package net.cnam.chateau.game;

public class Statistics {

    private long seed;
    private String playerName;
    private boolean bossDefeated = false;
    private int nbEnemyKilled = 0;
    private int nbRoomsVisited = 0;
    private int score;

    private boolean cheatModeActivate;

    public Statistics(long seed, String playerName) {
        this.playerName = playerName;
        this.seed = seed;
    }

    public void addAEnemyKill(){
        this.nbEnemyKilled +=1;
    }

    public void addARoomVisited(){
        this.nbRoomsVisited +=1;
    }

    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
    }
}
