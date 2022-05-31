package net.cnam.chateau.game;

public class Statistic {

    private long seed;
    private String playerName;
    private boolean bossDefeated = false;
    private int nbEnemyKilled = 0;
    private int nbRoomsVisited;
    private int nbRoomsCastle;
    private int score;

    private boolean cheatModeActivate;

    /**
     * Constructeur
     *
     * @param seed la graine de la carte utilisé
     * @param playerName le nom du joueur de la partie
     */
    public Statistic(long seed, String playerName) {
        this.playerName = playerName;
        this.seed = seed;
    }

    /**
     * Méthode incrémentant de 1 le nombre d'ennemie tué.
     */
    public void addAEnemyKill(){
        this.nbEnemyKilled +=1;
    }

    /**
     * Méthode permettant de signifier que le mode de triche a été activé impactant la non sauvegarde de la partie.
     */
    public void activeCheat(){
        this.cheatModeActivate = true;
    }

    /**
     * Setter permettant de définir le nombre de pièces visitées par le joueur.
     *
     * @param nbRoomsVisited nombre de pièces visitées
     */
    public void setNbRoomsVisited(int nbRoomsVisited) {
        this.nbRoomsVisited = nbRoomsVisited;
    }

    /**
     * Setter permettant de définir le nombre de pièces totales du château
     *
     * @param nbRoomsCastle
     */
    public void setNbRoomsCastle(int nbRoomsCastle) {
        this.nbRoomsCastle = nbRoomsCastle;
    }

    /**
     * Setter pour changer le nom défini dans les statistiques.
     *
     * @param playerName le nouveau nom à mettre
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Méthode permettant de définir si le boss a été vaincu ou non durant la partie.
     *
     * @param bossDefeated true le boss est vaincu / false le boss n'est pas vaincu
     */
    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
    }

    /**
     * Méthode pour simuler un calcul de score non exhaustif.
     */
    public void calculScore(){
        this.score = nbRoomsVisited / nbRoomsCastle * 1000;
        this.score += this.nbEnemyKilled * 100;
        if (this.bossDefeated){
            this.score += 2000;
        }
    }
}
